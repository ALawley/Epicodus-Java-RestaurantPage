import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfRestaurantToList() {
    Restaurant testRestaurant = new Restaurant("Lardo", "Pork-centric sandwich shop");
    Restaurant testRestaurant1 = new Restaurant("Grassa", "Italian fare");
    testRestaurant.save();
    testRestaurant1.save();
    assertEquals(2, Restaurant.all().size());
  }

  @Test
  public void updateName_changesRestaurantName() {
    Restaurant testRestaurant = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant.updateName("Bunk");
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals("Bunk", savedRestaurant.getName());
  }

  @Test
  public void updateDescription_changesRestaurantDescription() {
    Restaurant testRestaurant = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant.updateDescription("Sandwiches!");
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals("Sandwiches!", savedRestaurant.getDescription());
  }

  @Test
  public void delete_removesRestaurantFromDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo", "Pork-centric sandwich shop");
    testRestaurant.save();
    testRestaurant.delete();
    assertEquals(0, Restaurant.all().size());
  }

  @Test
  public void find_findsInstanceOfRestaurantById() {
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    testRestaurant.save();
    assertEquals(Restaurant.find(testRestaurant.getId()), testRestaurant);
  }

  @Test
  public void assignCuisine_assignsCuisineToTheRestaurant() {
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    testRestaurant.save();
    Cuisine testCuisine = new Cuisine("Sandwiches");
    testCuisine.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), testCuisine.getId());
  }

  @Test
  public void clearCuisine_removesCuisineFromRestaurant() {
    Cuisine testCuisine = new Cuisine("Thai");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("Bunk", "Sandwich shop");
    testRestaurant.save();
    testRestaurant.assignCuisine(testCuisine.getId());
    testRestaurant.clearCuisine();
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), 0);
  }
}
