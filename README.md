# Footway+ Fullstack Challenge | Backend

### This backend part was developed using Java 17, the Spring Boot framework, and MySQL.
The API supports CRUD operations for managing physical and digital products, as well as features like product search, order simulation, and mapping between physical and digital products.
</br>

[![Stack](https://skillicons.dev/icons?i=java,spring,mysql&theme=dark)](https://skillicons.dev)
</br>

### Requirements
- [X] Managing physical and digital products (CRUD)
- [X] Creating and managing mappings between physical and digital products
- [X] Searching for products
- [X] Simulating order placement
- [X] Your API should handle product attributes including
    - SKU
    - EAN
    - name
    - description
    - price
    - variants (like size/color)
- [X] Implement proper error handling
- [X] Choose and implement an appropriate data storage solution
- [X] Implement efficient search for free text search (Preferably at the backend/DB level)
- [X] Create a robust matching suggestion system based on EAN

</br>

### UML Diagrams

#### First Model

![UML_v1](https://github.com/user-attachments/assets/a2bece65-8eb0-4291-8d8f-752eb020b7bb)

The first model uses a single _Product_ table with a flag indicating whether a product is digital or physical. Very simple, but also no very practical and scalable. And perhaps a bit confusing. Also became more problematic at the time to map them.

Map is a Many to Many relation, which at first can be a bit weird to conceive but I have found at least a reason for each part:
- A Digital prodcut can be mapped to several Physical products because the digital prodcut can be a bundle/package including several prodcuts.
- A Physical prodcut can be mapped to several Digital products because we can be selling the product in different ways in the digital platform.


#### Second Model

![UML_v2](https://github.com/user-attachments/assets/d7332846-e143-4391-8e84-6c83360718a7)

Second model has a differentiation between Physical and Digital products, having two different models, but inhereting the basic properties from the parent table Product. This solution is more scalable for the future, as for now we might have the same properties for each Product, but in the future we could start to add different fields on each one like size, weight on the Physical Product or views, size on the Digital one. This is also easier now to map them, but still map is not quite scalable or managable, that's why there's a third option.

#### Third Model

![UML_v3](https://github.com/user-attachments/assets/0058ef06-8047-48a5-bfe8-1ed2409a4a2e)

Third model creates the Mapping table. For now we just have an id and the two mapped products. But in the future we could also have the time, the author or perhaps states of the mapping. This way also its easier to manage, as we can get a mapping by its _id_, and actually edit it. Also one reason behind it is to follow REST principles, having mappings as a resource and a model in our DB makes it able for the user to retreive instances by _id_, instead of having to do a query with both products _sku_. This way we have a clear resource on our API and more scalable options


#### Forth Model

![UML_v4](https://github.com/user-attachments/assets/ad29264e-c77d-4f43-b2c0-a79c2ccf5d37)

Forth model includes the order entity which for now only has ID. Each mapping now will belong to an order. Many to Many relation with digital product, as an order can have several digital products, and a digital product can be in severeal orders.



## Indexes

#### digital_product
- INDEX on SKU
- INDEX on EAN
- Composite FULL TEXT INDEX on (name, description) -> Improves efficiency when user searches a text by product name or description

#### physical_product
- INDEX on SKU
- INDEX on EAN
- Composite FULL TEXT INDEX on (name, description) -> Improves efficiency when user searches a text by product name or description

#### mappings
- INDEX on id
- Compsite INDEX on (digitalProduct_sku, order_id);
- Compsite INDEX on (physicalProduct_sku, order_id);

### order
- INDEX on id


### API Endpoints

#### Digital Products
| HTTP Method | Endpoint              | Description                          | Request Body / Params        | Response                          |
|-------------|-----------------------|--------------------------------------|------------------------------|------------------------------------|
| **GET**     | `/digital-products`           | Retrieve all products (physical and digital). | Query Params: `page`, `take`, `search` (optional) | List of products                  |
| **GET**     | `/digital-products/{sku}`     | Retrieve a specific product by SKU. | Path Param: `sku`            | Product details                   |
| **POST**    | `/digital-products`           | Create a new product (physical or digital). | JSON: Product details        | Created product details           |
| **PUT**     | `/digital-products/{sku}`     | Update an existing product.         | Path Param: `sku`, JSON: Product details | Updated product details           |
| **DELETE**  | `/digital-products/{sku}`     | Delete a product by SKU.            | Path Param: `sku`            | Success or error message          |

#### Physical Products
| HTTP Method | Endpoint              | Description                          | Request Body / Params        | Response                          |
|-------------|-----------------------|--------------------------------------|------------------------------|------------------------------------|
| **GET**     | `/physical-products`           | Retrieve all products (physical and digital). | Query Params: `page`, `take`, `search`, `ean` (optional) | List of products                  |
| **GET**     | `/physical-products/{sku}`     | Retrieve a specific product by SKU. | Path Param: `sku`            | Product details                   |
| **POST**    | `/physical-products`           | Create a new product (physical or digital). | JSON: Product details        | Created product details           |
| **PUT**     | `/physical-products/{sku}`     | Update an existing product.         | Path Param: `sku`, JSON: Product details | Updated product details           |
| **DELETE**  | `/physical-products/{sku}`     | Delete a product by SKU.            | Path Param: `sku`            | Success or error message          |

#### Mappings
| HTTP Method | Endpoint              | Description                          | Request Body / Params        | Response                          |
|-------------|-----------------------|--------------------------------------|------------------------------|------------------------------------|
| **GET**     | `/mappings`           | Retrieve all product mappings.      | Query Params: `physicalSku` (optional), `digitalSku` (optional), `orderId` (optional) | List of mappings                  |
| **GET**     | `/mappings/{id}`      | Retrieve a specific mapping by ID.  | Path Param: `id`             | Mapping details                   |
| **POST**    | `/mappings`           | Create a new mapping between products. | JSON: Mapping details (e.g., `digitalProductSku`, `physicalProductSku`) | Created mapping details           |
| **DELETE**  | `/mappings/{id}`      | Delete a mapping by ID.             | Path Param: `id`             | Success or error message          |

#### Orders
| HTTP Method | Endpoint                           | Description                          | Request Body / Params        | Response                          |
|-------------|------------------------------------|--------------------------------------|------------------------------|------------------------------------|
| **GET**     | `/orders`                         | Retrieve all orders.                | None                         | List of orders                    |
| **GET**     | `/orders/{id}`                    | Retrieve a specific order by ID.    | Path Param: `id`             | Order details                     |
| **GET**     | `/orders/{id}/digital-products`   | Retrieve digital products from order | Path Param: `id`, Query Params: `page`, `take`, `search` | List of digital products in order |
| **POST**    | `/orders`                         | Simulate placing a new order.       | JSON: Order details (e.g., product SKUs) | Created order details             |



