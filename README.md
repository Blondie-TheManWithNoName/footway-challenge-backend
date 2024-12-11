# Footway+ Fullstack Challenge | Backend

### Coded with Java v17 with Srping Boot Framework and MySQL
</br>

[![Stack](https://skillicons.dev/icons?i=java,spring,mysql&theme=dark)](https://skillicons.dev)

</br>

### Requisits
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

</br>

### UML Diagrams

#### First Model

![UML_v1](https://github.com/user-attachments/assets/a2bece65-8eb0-4291-8d8f-752eb020b7bb)

At first this is the first model I thought of, with one single _Product_ table with a flag to tell if the product is digital or physical. Very simple, but also no very practical and scalable. And perhaps a bit confusing. Also became more problematic at the time to map them.

Map is a Many to Many relation, which at first can be a bit weird to conceive but I have found at least a reason for each part:
- A Digital prodcut can be mapped to several Physical products because the digital prodcut can be a bundle/package including several prodcuts.
- A Physical prodcut can be mapped to several Digital products because we can be selling the product in different ways in the digital platform.


#### Second Model

![UML_v2](https://github.com/user-attachments/assets/d7332846-e143-4391-8e84-6c83360718a7)

Second model has a differntiation between Physical and Digital products, having two different models, but inhereting the basic properties from the parent table Product. This solution is more scalable for the future, as for now we might have the same properties for each Product, but in the future we could start to add different fields on each one like size, weight on the Physical Product or views, size on the Digital one. This is also easier now to map them, but still map is not quite scalable or managable, that's why there's a third option.

#### Third Model

![UML_v3](https://github.com/user-attachments/assets/0058ef06-8047-48a5-bfe8-1ed2409a4a2e)

Third model creates the Mapping table. For now we just have an id and the two mapped products. But in the future we could also have the time, the author or perhaps states of the mapping. This way also its easier to manage, as we can get a mapping by its _id_, and actually edit it. Also one reason behind it is to follow REST principles, having mappings as a resource and a model in our DB makes it able for the user to retreive instances by _id_, instead of having to do a query with both products _sku_. This way we have a clear resource on our API and more scalable options


#### Forth Model

![UML_v4](https://github.com/user-attachments/assets/ad29264e-c77d-4f43-b2c0-a79c2ccf5d37)

Forth model includes the order entity which for now only has ID. Each mapping now will belong to an order. Many to Many relation with digital prodcut, as an order can have several digital prodcuts, and a digital prodcut can be in severeal orders.



## Indexes

#### digital_product
- INDEX on SKU
- INDEX on EAN
- Composite FULL TEXT INDEX on (name, description)

#### physical_product
- INDEX on SKU
- INDEX on EAN
- Composite FULL TEXT INDEX on (name, description)

#### mappings
- INDEX on id
- INDEX on (digitalProduct_sku, order_id);
- INDEX on (physicalProduct_sku, order_id);

### order
- INDEX on id





