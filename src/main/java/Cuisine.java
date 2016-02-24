import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int cuisine_id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

// Cuisine.find(testRestaurant.getCuisineId()).getType()

  public int getId() {
    return cuisine_id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO cuisine(type) VALUES (:type)";
    this.cuisine_id = (int) con.createQuery(sql, true)
      .addParameter("type", type)
      .executeUpdate()
      .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT cuisine_id, type FROM cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    String sql = "UPDATE cuisine SET type = :type WHERE cuisine_id = :cuisine_id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("type", newType)
        .addParameter("cuisine_id", cuisine_id)
        .executeUpdate();
    }
  }
//
  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisine WHERE cuisine_id = :cuisine_id";
        con.createQuery(sql)
          .addParameter("cuisine_id", cuisine_id)
          .executeUpdate();
    }
  }

  public static Cuisine find(int cuisine_id) {
    String sql = "SELECT cuisine_id, type FROM cuisine WHERE cuisine_id = :cuisine_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetchFirst(Cuisine.class);
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT * FROM restaurants WHERE cuisine_id = :cuisine_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetch(Restaurant.class);
    }
  }

  public static List<Restaurant> getUnassignedRestaurants() {
    String sql = "SELECT * FROM restaurants WHERE cuisine_id IS NULL";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Restaurant.class);
    }
  }

  public static void deleteAll(){
  try(Connection con = DB.sql2o.open()) {
    String deleteCuisineQuery = "DELETE FROM cuisine *;";
    con.createQuery(deleteCuisineQuery).executeUpdate();
    }
  }


//   /******************************************************
//     Students:
//     TODO: Create method to get cuisine
//   *******************************************************/
//
}
