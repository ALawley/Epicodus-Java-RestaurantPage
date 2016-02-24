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
  public void all_addsAllInstancesOfRestaurantToList() {
    Restaurant testRestaurant = new Restaurant("Lardo");
    Restaurant testRestaurant1 = new Restaurant("Grassa");
    testRestaurant.save();
    testRestaurant1.save();
    assertEquals(2, Restaurant.all().size());
  }
}
