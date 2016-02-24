import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private int cuisine_id;

  public Restaurant (String name) {
    this.name = name;
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

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurants(name) VALUES (:name)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }

  //READ
  public static List<Restaurant> all() {
    String sql = "SELECT id, name FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  //UPDATE
  public void update(String newName) {
    this.name = newName ;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
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
    String sql = "SELECT id, name FROM restaurants WHERE id = :id";
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

//
//   /******************************************************
//     Students:
//     TODO: Create method to get cuisine type
//   *******************************************************/
//
}
