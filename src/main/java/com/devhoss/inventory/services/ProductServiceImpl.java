package com.devhoss.inventory.services;
import com.devhoss.inventory.model.Category;
import com.devhoss.inventory.model.Product;
import com.devhoss.inventory.repository.ICategoryRepository;
import com.devhoss.inventory.repository.IProductRepository;
import com.devhoss.inventory.response.ProductResponseRest;

import com.devhoss.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ICategoryRepository categoryDao;
    @Autowired
    private IProductRepository productDao;

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar categoria por id
            Optional<Category> category = categoryDao.findById(categoryId);

            if(category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //Salvamos producto
            Product productSave = productDao.save(product);

            if(productSave != null) {
                list.add(productSave);
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto guardado");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar producto por id
            Optional<Product> product = productDao.findById(id);

            if(product.isPresent()){
                byte[] imageDecompress = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDecompress);
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //buscar producto por name
            listAux = productDao.findByNameContainingIgnoreCase(name);

            if(listAux.size() > 0){
                for (Product product : listAux) {
                    byte[] imageDecompress = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDecompress);
                    list.add(product);
                }

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> deleteById(Long id) {
        ProductResponseRest response = new ProductResponseRest();

        try {
            //borrar producto por id
            productDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Producto eliminado");


        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //buscar producto
            listAux = (List<Product>) productDao.findAll();

            if(listAux.size() > 0){
                for (Product product : listAux) {
                    byte[] imageDecompress = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDecompress);
                    list.add(product);
                }

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                response.getProduct().setProducts(list);
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> update(Long id, Long categoryId, Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //buscar categoria por id
            Optional<Category> category = categoryDao.findById(categoryId);

            if(category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //buscamos producto a actualizar
            Optional<Product> productSearch = productDao.findById(id);

            if(productSearch.isPresent()) {
                //actualizamos producto

                productSearch.get().setName(product.getName());
                productSearch.get().setStock(product.getStock());
                productSearch.get().setPrice(product.getPrice());
                productSearch.get().setPicture(product.getPicture());
                productSearch.get().setCategory(product.getCategory());

                //salvamos producto en base de datos
                Product  productToUpdate = productDao.save(productSearch.get());

                if (productToUpdate != null){
                    list.add(productToUpdate);
                    response.setMetadata("Respuesta ok", "00", "Producto actualizado");
                    response.getProduct().setProducts(list);
                }else {
                    response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
                    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }
}
