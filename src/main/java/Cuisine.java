import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int mId;
  private String mType;

  public Cuisine (String type) {
    this.mType = type;
  }

  public int getId() {
    return mId;
  }

  public String getType() {
    return mType;
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

//   //CREATE
//   public void save() {
//     try (Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Create sql query and execute update
//       *******************************************************/
//     }
//   }
//
//   //READ
//   public static List<Cuisine> all() {
//     try (Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Create sql query and execute update
//       *******************************************************/
//     }
//   }
//
//   //UPDATE
//   public void update(String newType) {
//     this.mType = newType;
//     try(Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Create sql query and execute update
//       *******************************************************/
//     }
//   }
//
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       /******************************************************
//         Students: TODO: Create sql query and execute update
//       *******************************************************/
//     }
//   }
//
//   /******************************************************
//     Students:
//     TODO: Create find method
//     TODO: Create method to get restaurants
//   *******************************************************/
//
}
