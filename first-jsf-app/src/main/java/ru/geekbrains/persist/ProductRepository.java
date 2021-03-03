package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", create(), new BigDecimal(1000)));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", create(), new BigDecimal(2000)));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", create(), new BigDecimal(2000)));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAll", Product.class)
                .getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAll", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public Category create() {
        Category category = new Category();
        category.setName("First category");
        em.persist(category);
        return category;
    }

    @Transactional
    public Category findCategory(int id) {
        Category category = em.find(Category.class, id);
        return category;
    }

    @Transactional
    public List<Category> findAllCategories() {
        Query query = em.createQuery("findAllCategories", Category.class);
        List<Category> list = query.getResultList();
        return list;
    }

    @Transactional
    public void updateCategoryName(Long id, String newName) {
        Category category = em.find(Category.class, id);
        category.setName(newName);
        em.merge(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = em.find(Category.class, id);
        em.remove(category);
    }

}
