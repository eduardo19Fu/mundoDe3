package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.error.exceptions.NoContentException;
import com.aglayatech.mundo3.error.exceptions.NotFoundException;
import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.model.MovimientoCaja;
import com.aglayatech.mundo3.repository.IMovimientoCajaRepository;
import com.aglayatech.mundo3.service.ICajaService;
import com.aglayatech.mundo3.service.IMovimientoCajaService;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoCajaServiceImpl implements IMovimientoCajaService {

    private final IMovimientoCajaRepository movimientoCajaRepository;

    private final ICajaService cajaService;

    @Transactional(readOnly = true)
    @Override
    public List<MovimientoCaja> listarMovimientoCajas() {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            List<MovimientoCaja> movimientosCajas = movimientoCajaRepository.findAll();

            if (!movimientosCajas.isEmpty()) {
                log.info("Retornando listado de movimientos realizados");
                return movimientosCajas;
            } else {
                log.warn("No existen movimientos de caja registrados");
                throw new NoContentException("No existen movimientos de caja registrados");
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MovimientoCaja buscarMovimiento(Long id) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            Optional<MovimientoCaja> movimientoCaja = movimientoCajaRepository.findById(id);

            if(movimientoCaja.isPresent()) {
                log.info("Obteniendo movimiento de caja: {}", id);
                return movimientoCaja.get();
            } else {
                log.warn("No se encontró ningún movimiento con el ID: {}", id);
                throw new NotFoundException("No se encontró ningún movimiento con el ID: ".concat(id.toString()));
            }
        } catch(DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param caja
     * @return
     */
    @Override
    public List<MovimientoCaja> listarMovimientoCajas(Caja caja) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            List<MovimientoCaja> movimientosCajas = movimientoCajaRepository.findByCaja(caja);

            if (!movimientosCajas.isEmpty()) {
                log.info("Retornando listado de movimientos realizados para la caja: {}", caja.getIdCaja());
                return movimientosCajas;
            } else {
                log.warn("No existen movimientos de caja registrados para la caja: {}", caja.getIdCaja());
                throw new NoContentException("No existen movimientos de caja registrados para la caja: ".concat(caja.getIdCaja().toString()));
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Override
    public List<MovimientoCaja> listarMovimientoCajas(LocalDate fecha1, LocalDate fecha2) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            List<MovimientoCaja> movimientoCajas = movimientoCajaRepository.findByFechaMovimientoBetween(fecha1, fecha2);
            if (!movimientoCajas.isEmpty()) {
                log.info("Retornando listado de movimientos realizados entre {} y {}", fecha1, fecha2);
                return movimientoCajas;
            } else {
                log.warn("No existen movimientos registrados entre {} y el {}", fecha1, fecha2);
                throw new NoContentException("No existen movimientos registrados entre el ".concat(fecha1.toString()).concat(" y el ".concat(fecha2.toString())));
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(rollbackFor = {DataAccessException.class, Exception.class})
    @Override
    public MovimientoCaja crearMovimientoCaja(MovimientoCaja movimientoCaja) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        Caja caja = null;
        Caja cajaSaved = null;
        MovimientoCaja movimientoCajaSaved = null;

        try {
            movimientoCajaSaved = movimientoCajaRepository.save(movimientoCaja);
            if(movimientoCajaSaved != null) {
                caja = movimientoCajaSaved.getCaja();
                BigDecimal montoMovimiento = movimientoCaja.getMontoMovimiento();
                BigDecimal montoCajaActual = caja.getMontoActual();

                switch(movimientoCajaSaved.getTipoMovimiento()) {
                    case INGRESO:
                        caja.setMontoActual(montoMovimiento.add(montoCajaActual));
                        break;
                    case GASTO:
                    case DEVOLUCION:
                    case PAGO_PROVEEDOR:
                    case PRESTAMO:
                        caja.setMontoActual(montoCajaActual.subtract(montoMovimiento));
                        break;
                    default:
                        log.warn("No existe el movimiento seleccionado");
                        break;
                }

                cajaSaved = cajaService.guardar(caja);

                return (cajaSaved != null ? movimientoCajaSaved : null);
            } else {
                log.error("No se pudo llevar a cabo el registro del movimiento");
                return null;
            }

        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Override
    public MovimientoCaja actualizarMovimientoCaja(MovimientoCaja movimientoCaja) {
        // TODO: Posiblemente no sea necesario implementar
        return null;
    }

    @Override
    public void eliminarMovimientoCaja(MovimientoCaja movimientoCaja) {
        // TODO: Posiblemente no sea necesario implementar
    }

}
