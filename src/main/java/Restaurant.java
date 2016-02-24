import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;

  public Restaurant (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
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
    con.createQuery(sql)
      .addParameter("name", name)
      .executeUpdate();
    }
  }

  //READ
  public static List<Restaurant> all() {
    String sql = "SELECT id, name FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

//   //UPDATE
//   public void update(String newName) {
//     this.mName = newName;
//     try(Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Display all restaurants on main page
//       *******************************************************/
//       }
//   }
//
//   //DELETE
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Display all restaurants on main page
//       *******************************************************/
//     }
//   }
//
//   /******************************************************
//     Students:
//     TODO: Create find method
//     TODO: Create method to get cuisine type
//   *******************************************************/
//
}
