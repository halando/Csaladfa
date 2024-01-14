import java.sql.*;

public class Csaladfa {

 private static Connection getConnection() throws SQLException {
 String url = "jdbc:mysql://localhost:3306/your_database_name";
 String user = "your_username";
 String password = "your_password";
 return DriverManager.getConnection(url, user, password);
 }

 public static void adatbevitel(String nev, String szuletesiDatum, String szuletesiHely,
 String anyaNeve, String apaNeve, String elhalalozasIdopont, String elhalalozasHelye) {
 try (Connection connection = getConnection()) {
 String insertQuery = "INSERT INTO Csaladtagok (nev, szuletesi_datum, szuletesi_hely, anya_neve, apa_neve, elhalalozas_idopont, elhalalozas_helye) " +
 "VALUES (?, ?, ?, ?, ?, ?, ?)";
 try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
 preparedStatement.setString(1, nev);
 preparedStatement.setDate(2, Date.valueOf(szuletesiDatum));
 preparedStatement.setString(3, szuletesiHely);
 preparedStatement.setString(4, anyaNeve);
 preparedStatement.setString(5, apaNeve);
 preparedStatement.setDate(6, (elhalalozasIdopont != null) ? Date.valueOf(elhalalozasIdopont) : null);
 preparedStatement.setString(7, elhalalozasHelye);

 preparedStatement.executeUpdate();
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }

 public static void modositHibasAdatok(String nev, String ujSzuletesiDatum) {
 try (Connection connection = getConnection()) {
 String updateQuery = "UPDATE Csaladtagok SET szuletesi_datum = ? WHERE nev = ?";
 try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
 preparedStatement.setDate(1, Date.valueOf(ujSzuletesiDatum));
 preparedStatement.setString(2, nev);

 preparedStatement.executeUpdate();
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }

 public static void adatokKonkretSzemelyrol(String nev) {
 try (Connection connection = getConnection()) {
 String selectQuery = "SELECT * FROM Csaladtagok WHERE nev = ?";
 try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
 preparedStatement.setString(1, nev);

 try (ResultSet resultSet = preparedStatement.executeQuery()) {
 while (resultSet.next()) {
 System.out.println("Név: " + resultSet.getString("nev"));
 System.out.println("Születési dátum: " + resultSet.getDate("szuletesi_datum"));
 System.out.println("Születési hely: " + resultSet.getString("szuletesi_hely"));
 System.out.println("Anyja neve: " + resultSet.getString("anya_neve"));
 System.out.println("Apja neve: " + resultSet.getString("apa_neve"));
 System.out.println("Elhalálozás időpontja: " + resultSet.getDate("elhalalozas_idopont"));
 System.out.println("Elhalálozás helye: " + resultSet.getString("elhalalozas_helye"));
 }
 }
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }

 public static void kiknekGyerekei() {
 try (Connection connection = getConnection()) {
 String selectQuery = "SELECT anya_neve, apa_neve, GROUP_CONCAT(nev) AS gyerekek " +
 "FROM Csaladtagok " +
 "WHERE anya_neve IS NOT NULL AND apa_neve IS NOT NULL " +
 "GROUP BY anya_neve, apa_neve";
 try (Statement statement = connection.createStatement()) {
 try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
 while (resultSet.next()) {
 String szulok = resultSet.getString("anya_neve") + " és " + resultSet.getString("apa_neve");
 String gyerekek = resultSet.getString("gyerekek");
 System.out.println(szulok + " gyerekei: " + gyerekek);
 }
 }
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }

 public static void kinekGyermekei() {
 try (Connection connection = getConnection()) {
 String selectQuery = "SELECT nev, GROUP_CONCAT(gyerek_neve) AS gyermekek " +
 "FROM Csaladtagok " +
 "LEFT JOIN (" +
 " SELECT apa_neve AS szulo, nev AS gyerek_neve " +
 " FROM Csaladtagok " +
 " WHERE apa_neve IS NOT NULL " +
 " UNION " +
 " SELECT anya_neve AS szulo, nev AS gyerek_neve " +
 " FROM Csaladtagok " +
 " WHERE anya_neve IS NOT NULL" +
 ") AS Gyermekek ON Csaladtagok.nev = Gyermekek.szulo " +
 "GROUP BY nev";
 try (Statement statement = connection.createStatement()) {
 try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
 while (resultSet.next()) {
 String szuloNev = resultSet.getString("nev");
 String gyermekek = resultSet.getString("gyermekek");
 System.out.println(szuloNev + " gyermekei: " + gyermekek);
 }
 }
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
}