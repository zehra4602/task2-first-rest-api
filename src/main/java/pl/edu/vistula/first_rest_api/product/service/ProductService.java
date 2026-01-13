package pl.edu.vistula.first_rest_api.product.service;
import org.springframework.stereotype.Service;
import pl.edu.vistula.first_rest_api.product.api.request.ProductRequest;
import pl.edu.vistula.first_rest_api.product.api.response.ProductResponse;
import pl.edu.vistula.first_rest_api.product.domain.Product;
import pl.edu.vistula.first_rest_api.product.repository.ProductRepository;
import pl.edu.vistula.first_rest_api.product.support.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;
import pl.edu.vistula.first_rest_api.product.support.exception.ProductNotFoundException;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = ProductMapper.toProduct(request);
        Product saved = repository.save(product);
        return ProductMapper.toResponse(saved);
    }

    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.setName(request.getName());
        existing.setPrice(request.getPrice());

        Product saved = repository.save(existing);
        return ProductMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }

}

