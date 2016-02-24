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
    Restaurant testRestaurant = new Restaurant("Lardo");
    Restaurant testRestaurant1 = new Restaurant("Grassa");
    testRestaurant.save();
    testRestaurant1.save();
    assertEquals(2, Restaurant.all().size());
  }

  @Test
  public void update_changesRestaurantName() {
    Restaurant testRestaurant = new Restaurant("Lardo");
    testRestaurant.save();
    testRestaurant.update("Bunk");
    assertEquals("Bunk", testRestaurant.getName());
  }

  @Test
  public void delete_removesRestaurantFromDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo");
    testRestaurant.save();
    testRestaurant.delete();
    assertEquals(0, Restaurant.all().size());
  }

  @Test
  public void find_findsInstanceOfRestaurantById() {
    Restaurant testRestaurant = new Restaurant("Bunk");
    testRestaurant.save();
    assertEquals(Restaurant.find(testRestaurant.getId()), testRestaurant); 
  }
}
