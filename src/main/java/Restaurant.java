import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private int cuisine_id;
  private String description;

  public Restaurant (String name, String description) {
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId() &&
        this.getDescription().equals(newRestaurant.getDescription());
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurants(name, description) VALUES (:name, :description)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("description", description)
      .executeUpdate()
      .getKey();
    }
  }

  //READ
  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  //UPDATE
  public void updateName(String newName) {
    this.name = newName ;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDescription(String newDescription) {
    this.description = newDescription ;
    String sql = "UPDATE restaurants SET description = :description WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("description", newDescription)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
//
  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public static Restaurant find(int id) {
    String sql = "SELECT * FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
    }
  }

  public void assignCuisine(int cuisine_id) {
    this.cuisine_id = cuisine_id ;
    String sql = "UPDATE restaurants SET cuisine_id = :cuisine_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static void deleteAll(){
  try(Connection con = DB.sql2o.open()) {
    String deleteRestaurantQuery = "DELETE FROM restaurants *;";
    con.createQuery(deleteRestaurantQuery).executeUpdate();
    }
  }

//
//   /******************************************************
//     Students:
//     TODO: Create method to get cuisine type
//   *******************************************************/
//
}
