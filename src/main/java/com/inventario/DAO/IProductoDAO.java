package com.inventario.DAO;

import org.springframework.data.repository.CrudRepository;

import com.inventario.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto,Long> {

}
