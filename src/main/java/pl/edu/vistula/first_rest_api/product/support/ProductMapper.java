package pl.edu.vistula.first_rest_api.product.support;
import pl.edu.vistula.first_rest_api.product.api.request.ProductRequest;
import pl.edu.vistula.first_rest_api.product.api.response.ProductResponse;
import pl.edu.vistula.first_rest_api.product.domain.Product;
public class ProductMapper {
    public static Product toProduct(ProductRequest request) {
        return new Product(null, request.getName(), request.getPrice());
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
