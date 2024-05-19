package com.aglayatech.mundo3.service;

import com.aglayatech.mundo3.model.Proveedor;

import java.util.List;

public interface IProveedorService {

    public List<Proveedor> getAll();

    public Proveedor getProveedor(Integer idproveedor);

    public Proveedor save(Proveedor proveedor);

    public Proveedor delete(Proveedor proveedor);
}
