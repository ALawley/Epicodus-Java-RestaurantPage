import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfCuisineToList() {
    Cuisine testCuisine = new Cuisine("Thai");
    Cuisine testCuisine1 = new Cuisine("BBQ");
    testCuisine.save();
    testCuisine1.save();
    assertEquals(2, Cuisine.all().size());
  }

  @Test
  public void update_changesCuisineType() {
    Cuisine testCuisine = new Cuisine("Thai");
    testCuisine.save();
    testCuisine.update("Indian");
    Cuisine savedCuisine = Cuisine.find(testCuisine.getId());
    assertEquals("Indian", savedCuisine.getType());
  }

  @Test
  public void delete_removesCuisineFromDatabase() {
    Cuisine testCuisine = new Cuisine("Thai");
    testCuisine.save();
    testCuisine.delete();
    assertEquals(0, Cuisine.all().size());
  }

  @Test
  public void delete_removesCuisineFromRestaurantsAssignedWithIt() {
    Cuisine testCuisine = new Cuisine("Thai");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    testRestaurant.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    testCuisine.delete();
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), 0);
  }

  @Test
  public void find_findsInstanceOfCuisineById() {
    Cuisine testCuisine = new Cuisine("Indian");
    testCuisine.save();
    assertEquals(Cuisine.find(testCuisine.getId()), testCuisine);
  }

  @Test
  public void getRestaurants_getAllRestaurantsWithinACuisine() {
    Cuisine testCuisine = new Cuisine("Sandwiches");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    Restaurant testRestaurant1 = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant1.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    testRestaurant1.assignCuisine(testCuisine.getId());
    assertEquals(2,testCuisine.getRestaurants().size());
  }

  @Test
  public void getUnassignedRestaurants_getAllRestaurantsWithoutACuisine() {
    Cuisine testCuisine = new Cuisine("Sandwiches");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    Restaurant testRestaurant1 = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant1.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    assertEquals(1,Cuisine.getUnassignedRestaurants().size());
  }

  @Test
  public void clearAllAssigned_unassignsAllRestaurantsAssignedToCuisine() {
    Cuisine testCuisine = new Cuisine("Sandwiches");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    Restaurant testRestaurant1 = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant1.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    testRestaurant1.assignCuisine(testCuisine.getId());
    testCuisine.clearAllAssigned();
    assertEquals(0, testCuisine.getRestaurants().size());
  }
}
