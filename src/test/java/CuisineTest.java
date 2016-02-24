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
    assertEquals("Indian", testCuisine.getType());
  }

  @Test
  public void delete_removesCuisineFromDatabase() {
    Cuisine testCuisine = new Cuisine("Thai");
    testCuisine.save();
    testCuisine.delete();
    assertEquals(0, Cuisine.all().size());
  }

  @Test
  public void find_findsInstanceOfCuisineById() {
    Cuisine testCuisine = new Cuisine("Indian");
    testCuisine.save();
    assertEquals(Cuisine.find(testCuisine.getId()), testCuisine);
  }
}
