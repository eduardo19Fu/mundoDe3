package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.error.exceptions.NotFoundException;
import com.aglayatech.mundo3.model.Usuario;
import com.aglayatech.mundo3.model.enums.EstadoCajaEnum;
import org.springframework.dao.DataAccessException;
import com.aglayatech.mundo3.error.exceptions.NoContentException;
import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.repository.ICajaRepository;
import com.aglayatech.mundo3.service.ICajaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CajaServiceImpl implements ICajaService {

    private final ICajaRepository cajaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Caja> getCajas() {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            log.info("Buscando cajas");
            List<Caja> cajas = cajaRepository.findAll();

            if(!cajas.isEmpty()) {
                log.info("Listando cajas encontradas");
                return cajas;
            } else {
                log.info("No existen cajas registradas");
                throw new NoContentException("No existen cajas registradas");
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
    public List<Caja> getCajas(LocalDate fechaIni, LocalDate fechaFin) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            log.info("Buscando cajas por rango de fecha");
            List<Caja> cajas = cajaRepository.findByFechaAperturaBetween(fechaIni, fechaFin);

            if(!cajas.isEmpty()) {
                log.info("Listando cajas aperturadas entre {} y {}", fechaIni.toString(), fechaFin.toString());
                return cajas;
            } else {
                log.warn("No existen cajas aperturadas en el rago de fechas indicado");
                throw new NoContentException("No existen cajas aperturadas en el rango de fechas entre "
                            .concat(fechaIni.toString()).concat(" y ").concat(fechaFin.toString()));
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    /**
     * @param idusuario
     * @param estado
     * @return
     */
    @Override
    public Caja getCajaByIdUsuario(Integer idusuario, String estado) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            Optional<Caja> caja = cajaRepository.findCajaByUsuarioAndEstado(idusuario, estado);
            log.info("Devolviendo caja para el usuario: {}", idusuario);
            return caja.orElse(null);
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Caja getCaja(Long idcaja) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        Caja caja = null;
        try {
            caja = cajaRepository.findById(idcaja).orElse(null);

            if (caja != null) {
                log.info("Retornando caja con ID: {}", idcaja);
                return caja;
            } else {
                log.warn("Caja con ID: {} no se encuentra registrada.", idcaja);
                throw new NotFoundException("Caja con ID: " + idcaja + " no se encuentra registrada");
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Transactional(rollbackFor = {DataAccessException.class, Exception.class})
    @Override
    public Caja guardar(Caja caja) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        Caja cajaSaved = null;

        try {
            if(caja.getIdCaja() == null) {
                if(!comprobarCajaAperturada(caja.getUsuario(), EstadoCajaEnum.APERTURADA)) {
                    log.info("Registrando nueva caja: {}", caja);
                    caja.setEstado(EstadoCajaEnum.APERTURADA);
                    caja.setMontoActual(caja.getMontoApertura());
                    caja.setFechaApertura(LocalDateTime.now());
                    cajaSaved = cajaRepository.save(caja);
                } else {
                    log.warn("Usuario {}, ya posee una caja aperturada", caja.getUsuario().getUsuario());
                    return null;
                }
            } else {
                log.info("Actualizando caja: {}", caja);
                cajaSaved = cajaRepository.save(caja);
            }

            return cajaSaved;
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    @Transactional(rollbackFor = {DataAccessException.class, Exception.class})
    @Override
    public void eliminarCaja(Long idcaja) {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {
            Caja caja = cajaRepository.findById(idcaja).orElse(null);

            if(caja != null) {
                log.info("Eliminando caja con ID: {}", idcaja);
                cajaRepository.deleteById(caja.getIdCaja());
            } else {
                log.warn("Caja con ID: {} no existe", idcaja);
                throw new NotFoundException("Caja con ID: " + idcaja + " no existe ");
            }
        } catch (DataAccessException e) {
            log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
            throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
        } finally {
            log.debug("{} Exit", __method);
        }
    }

    private boolean comprobarCajaAperturada(Usuario usuario, EstadoCajaEnum estado) {
        return cajaRepository.findByUsuario(usuario, estado).isPresent();
    }
}
