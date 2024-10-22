package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.error.exceptions.NoContentException;
import com.aglayatech.mundo3.error.exceptions.NotFoundException;
import com.aglayatech.mundo3.model.DetalleFactura;
import com.aglayatech.mundo3.model.DetalleNotaCredito;
import com.aglayatech.mundo3.model.NotaCredito;
import com.aglayatech.mundo3.model.MovimientoProducto;
import com.aglayatech.mundo3.model.Producto;
import com.aglayatech.mundo3.model.Usuario;
import com.aglayatech.mundo3.model.enums.TipoMovimientoProductoEnum;
import com.aglayatech.mundo3.repository.INotaCreditoRepository;
import com.aglayatech.mundo3.service.INotaCreditoService;
import com.aglayatech.mundo3.service.IEstadoService;
import com.aglayatech.mundo3.service.IFacturaService;
import com.aglayatech.mundo3.service.IMovimientoProductoService;
import com.aglayatech.mundo3.service.IProductoService;
import com.aglayatech.mundo3.service.IUsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotaCreditoServiceImpl implements INotaCreditoService {

    private final INotaCreditoRepository notaCreditoRepository;
    private final IProductoService productoService;
    private final IUsuarioService usuarioService;
    private final IFacturaService facturaService;
    private final IEstadoService estadoService;
    private final IMovimientoProductoService movimientoProductoService;

    @Transactional(readOnly = true)
    @Override
    public List<NotaCredito> findAll() {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);
        try {
            List<NotaCredito> devoluciones = notaCreditoRepository.findAll();
            if(!devoluciones.isEmpty()) {
                log.info("Retornando listado de devoluciones: {}", devoluciones.size());
                return devoluciones;
            } else {
                log.warn("No se encontraron devoluciones registradas");
                throw new NoContentException("No se encontraron devoluciones registradas");
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.getMessage());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException("Ha ocurrido un error a nivel de base de datos", e.getCause());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public NotaCredito findNotaCredito(Long id) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            Optional<NotaCredito> devolucion = notaCreditoRepository.findById(id);
            if(devolucion.isPresent()) {
                log.info("Retornando NotaCredito con ID: {}", id);
                return devolucion.get();
            } else {
                log.warn("No se encontro devolucion con ID: {}", id);
                throw new NotFoundException("No se encontro devolucion con ID: " + id);
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.getMessage());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException("Ha ocurrido un error a nivel de base de datos", e.getCause());
        }
    }

    @Transactional(rollbackFor = {Exception.class, com.aglayatech.mundo3.error.exceptions.DataAccessException.class})
    @Override
    public NotaCredito save(NotaCredito notaCredito) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);
        boolean elementoNoEncontrado = true;
        NotaCredito newNotaCredito = null;

        try {
            List<DetalleFactura> itemsFactura = facturaService.findFacturaBySerieAndComprobante(notaCredito.getSerieComprobante(),
                    notaCredito.getNoComprobante()).getItemsFactura();

            if(!itemsFactura.isEmpty()) {
                elementoNoEncontrado = validarProductosEnFactura(notaCredito, itemsFactura);

                if (elementoNoEncontrado) {
                    log.info("**************** Registrando Nueva NotaCredito ****************");
                    newNotaCredito = notaCreditoRepository.save(notaCredito);

                    if (newNotaCredito.getIdNotaCredito() != null) {

                        for (DetalleNotaCredito item : newNotaCredito.getItems()) {
                            movimientoProductoService.save(
                                    buildMovimiento(item.getProducto(), item.getCantidadDevuelta(), TipoMovimientoProductoEnum.DEVOLUCION, newNotaCredito.getUsuario())
                            );
                        }
                    } else {
                        log.error("No se pudo crear la devolución");
                        return null;
                    }
                    return newNotaCredito;
                } else {
                    log.error("Existen productos en el detalle de la Nota de Credito que no estan en el detalle de la venta");
                    throw new NotFoundException("Existen productos en el detalle de la Nota de Credito que no estan en el detalle de la venta");
                }
            } else {
                log.error("La compra registrada con el comprobante {}, no se encuentra registrada", notaCredito.getNoComprobante());
                throw new NotFoundException("La venta registrada con el comprobante " + notaCredito.getNoComprobante() + ", no se encuentra registrada");
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.getMessage());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException("Ha ocurrido un error a nivel de base de datos", e.getCause());
        }
    }

    @Override
    public void delete(NotaCredito notaCredito) {
        // TODO: Determinar si es necesario la programación de la eliminación de este servicio.
    }

    public MovimientoProducto buildMovimiento(Producto producto, int cantidad, TipoMovimientoProductoEnum tipoMovimiento, Usuario usuario) {
        return MovimientoProducto.builder()
                .producto(producto)
                .stockInicial(producto.getStock())
                .cantidad(cantidad)
                .tipoMovimiento(tipoMovimiento)
                .usuario(usuario)
                .build();
    }

    private boolean validarProductosEnFactura(NotaCredito notaCredito, List<DetalleFactura> itemsFactura) {
        for(DetalleNotaCredito itemNota : notaCredito.getItems()) {
            Producto productoNota = itemNota.getProducto();

            boolean productoEncontrado = false;

            for(DetalleFactura itemFactura : itemsFactura) {
                if(itemFactura.getProducto().getIdProducto().equals(productoNota.getIdProducto())) {
                    productoEncontrado = true;
                    break;
                }
            }

            if(!productoEncontrado) {
                log.error("El producto con codigo: {}, no se encuentra en el detalle de la venta, por lo que no puede ser devuelto.", productoNota.getCodProducto());
                return false;
            }
        }

        return true;
    }
}
