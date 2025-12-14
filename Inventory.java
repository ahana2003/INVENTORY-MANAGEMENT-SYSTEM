import java.util.ArrayList;

public class Inventory {

    ArrayList<Product> products = new ArrayList<>();

    // Add product
    public void addProduct(Product p) {
        products.add(p);
        System.out.println("✅ Product added successfully!");
    }

    // View products
    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("❌ No products available.");
            return;
        }

        for (Product p : products) {
            System.out.println(
                    "ID: " + p.id +
                            ", Name: " + p.name +
                            ", Price: " + p.price +
                            ", Qty: " + p.quantity
            );
        }
    }

    // Update product quantity
    public void updateProduct(int id, int newQty) {
        for (Product p : products) {
            if (p.id == id) {
                p.quantity = newQty;
                System.out.println("✅ Quantity updated successfully!");
                return;
            }
        }
        System.out.println("❌ Product not found.");
    }

    // Delete product
    public void deleteProduct(int id) {
        for (Product p : products) {
            if (p.id == id) {
                products.remove(p);
                System.out.println("✅ Product deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Product not found.");
    }
}
