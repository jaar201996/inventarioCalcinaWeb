package com.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.DAO.IProductoDAO;
import com.inventario.entity.Producto;


@Service
public class ProductoServiceImple implements IProductoService{
 
	@Autowired
	private IProductoDAO productoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {

		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productoDao.deleteById(id);
	}
}
