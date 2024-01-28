
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
// import File



enum SmartVehicleType {
    BIKE, BICYCLE, MOPED
}
enum ConditionType {
    GOOD, BAD, FAIR
}
enum PaymentType {
    CREDIT_CARD, DEBIT_CARD, NET_BANKING, UPI
}

enum UserType {
    STUDENT, FACULTY, STAFF
}

class Address {
    private String address;
    private String city;
    private String landmark;
    private String zipcode;

    public Address(String address, String city, String landmark, String zipcode) {
        this.address = address;
        this.city = city;
        this.landmark = landmark;
        this.zipcode = zipcode;
    } 

    // Getters and Setters

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class User {
    private String email;
    private String password;
    private String name;
    private Address address;
    private UserType userType;
    private List<ID> userId;
    private Wallet wallet;
    private boolean walletAutoDeduct;
    private boolean payFromWallet;
    private List<Trip> tripHistory;

    public User (String email, String password, String name, Address address, UserType userType, List<ID> userId){
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.userType = userType;
        this.userId = userId;
        this.wallet = new Wallet(0.0f, 100.0f);
        this.walletAutoDeduct = false;
        this.payFromWallet = true;
        this.tripHistory = new ArrayList<Trip>();
    }

    // Getter and Setter methods
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<ID> getUserId() {
        return userId;
    }

    public void setUserId(List<ID> userId) {
        this.userId = userId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isWalletAutoDeduct() {
        return walletAutoDeduct;
    }

    public void setWalletAutoDeduct(boolean walletAutoDeduct) {
        this.walletAutoDeduct = walletAutoDeduct;
    }

    public boolean isPayFromWallet() {
        return payFromWallet;
    }

    public void setPayFromWallet(boolean payFromWallet) {
        this.payFromWallet = payFromWallet;
    }

    public List<Trip> getTripHistory() {
        return tripHistory;
    }

    public void setTripHistory(List<Trip> tripHistory) {
        this.tripHistory = tripHistory;
    }

    public boolean bookTrip(String qrCode, TripManager tripManager) {
        Trip trip = tripManager.startTrip(this, qrCode);
        if(trip == null) {
            return false;
        }
        tripHistory.add(trip);
        return true;
    }

    public SupportDocument fetchSupportDoc(SmartVehicle vehicle){
        return vehicle.getSupportDocument();
    }

    public boolean finishTrip(Trip trip, TripManager tripManager) {
        tripManager.endTrip(this, trip, null);
        return true;
    }

    public boolean addTripToHistory(Trip trip){
        tripHistory.add(trip);
        return true;
    }

    public Feedback giveFeedback(Trip trip, float rating, String comment) {
        Feedback feedback = new Feedback(-1, rating, comment);
        // add feedback to database
        return feedback;
    }

    public boolean addMoneyToWallet(float amount, PaymentType paymentType) {
        wallet.addMoney(amount, paymentType);
        return true;
    }
}



abstract class Card {
    private int cardNo;
    private Date expiryDate;
    private int cvv;

    public Card(int cardNo, Date expiryDate, int cvv) {
        this.cardNo = cardNo;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    // getters
    public int getCardNo() {
        return cardNo;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getCvv() {
        return cvv;
    }
}

class DebitCard extends Card {
    public DebitCard(int cardNo, Date expiryDate, int cvv) {
        super(cardNo, expiryDate, cvv);
    }
    
    public boolean addMoneyUsingDebitCard(float amount) {
        // Implementation logic for adding money using Debit Card
        // Return true if successful, false otherwise
        return true;
    }
}

class CreditCard extends Card {
    public CreditCard(int cardNo, Date expiryDate, int cvv) {
        super(cardNo, expiryDate, cvv);
    }
    public boolean addMoneyUsingCreditCard(float amount) {
        // Implementation logic for adding money using Credit Card
        // Return true if successful, false otherwise
        return true;
    }
}

class Condition {
    private int miles;
    private ConditionType engineStatus;
    private float tirePressure;
    private ConditionType brakes;
    private ConditionType lights;

    public Condition(int miles, ConditionType engineStatus, float tirePressure, ConditionType brakes, ConditionType lights) {
        this.miles = miles;
        this.engineStatus = engineStatus;
        this.tirePressure = tirePressure;
        this.brakes = brakes;
        this.lights = lights;
    }
    
    public int getMiles() {
        return this.miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public ConditionType getEngineStatus() {
        return this.engineStatus;
    }

    public ConditionType getBrakes() {
        return this.brakes;
    }

    public ConditionType getLights() {
        return this.lights;
    }

    public void setEngineStatus(ConditionType engineStatus) {
        this.engineStatus = engineStatus;
    }

    public float getTirePressure() {
        return this.tirePressure;
    }

    public void setTirePressure(float tirePressure) {
        this.tirePressure = tirePressure;
    }

    public void setBrakes(ConditionType brakes) {
        this.brakes = brakes;
    }

    public void setLights(ConditionType lights) {
        this.lights = lights;
    }
}

class DockingStation {
    private int dockingStationId;
    private Address address;
    private String name;
    private Map<SmartVehicleType, Integer> capacity;
    private List<SmartVehicle> vehicles;
    private Map<SmartVehicle, Integer> numberOfVehicles;
    private Map<SmartVehicle, List<Date>> vehicleLog;
    private Map<Date, String> maintainenceLog;
    private String SecurityFeatures;
    private Map<Date, String> securityEventsLog;

    public DockingStation(int dockingStationId, Address address, String name, Map<SmartVehicleType, Integer> capacity, String securityFeatures){
        this.dockingStationId = dockingStationId;
        this.address = address;
        this.name = name;
        this.capacity = capacity;
        this.vehicles = new ArrayList<SmartVehicle>();
        this.numberOfVehicles = new HashMap<SmartVehicle, Integer>();
        this.vehicleLog = new HashMap<SmartVehicle, List<Date>>();
        this.maintainenceLog = new HashMap<Date, String>();
        this.SecurityFeatures = securityFeatures;
        this.securityEventsLog = new HashMap<Date, String>();
    }

    public int getDockingStationId() {
        return dockingStationId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Map<SmartVehicleType, Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(Map<SmartVehicleType, Integer> capacity) {
        this.capacity = capacity;
    }

    public List<SmartVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<SmartVehicle> vehicles) {
        this.vehicles = vehicles;
    }
    
    public Map<SmartVehicle, Integer> getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(Map<SmartVehicle, Integer> numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public Map<SmartVehicle, List<Date>> getVehicleLog() {
        return vehicleLog;
    }

    public Map<Date, String> getMaintainenceLog() {
        return maintainenceLog;
    }

    public void setMaintainenceLog(Map<Date, String> maintainenceLog) {
        this.maintainenceLog = maintainenceLog;
    }

    public String getSecurityFeatures() {
        return SecurityFeatures;
    }

    public void setSecurityFeatures(String securityFeatures) {
        SecurityFeatures = securityFeatures;
    }

    public Map<Date, String> getSecurityEventsLog() {
        return securityEventsLog;
    }

    public void setSecurityEventsLog(Map<Date, String> securityEventsLog) {
        this.securityEventsLog = securityEventsLog;
    }

    public void logVehicleEntry(SmartVehicle vehicle, Date time) {
        // Logs the entry of a vehicle
    }

    public void logVehicleExit(SmartVehicle vehicle, Date time) {
        // Logs the exit of a vehicle
    }

    public void logMaintenance(Date time, String description) {
        // Logs the maintenance of a vehicle
    }

    public void logSecurityEvent(Date time, String description) {
        // Logs the security event
    }
}
class Feedback {
    private int feedbackId;
    private float rating;
    private String comment;

    public Feedback(int feedbackId, float rating, String comment) {
        this.feedbackId = -1; // main system maintain a counter for every class, instantiating increments the counter which assigns ids
        this.rating = rating;
        this.comment = comment;
    }

    // Getter and Setter methods
    public int getFeedbackId() {
        return feedbackId;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

class GPS {
  private int latitude;
  private int longitude;

  public GPS(int latitude, int longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public int getLatitude() {
    return latitude;
  }

  public int getLongitude() {
    return longitude;
  }

  public void setLatitude(int latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(int longitude) {
    this.longitude = longitude;
  }
}

class ID {
    private String idNumber;
    private Date expiry;

    public ID(String idNumber, Date expiry) {
        this.idNumber = idNumber;
        this.expiry = expiry;
    }

    // Getter and Setter methods
    public String getIdNumber() {
        return idNumber;
    }

    public Date getExpiry() {
        return expiry;
    }
}class NetBanking {
    private int accountNo;

    public NetBanking(int accountNo) {
        this.accountNo = accountNo;
    }
    public boolean netBanking(float amount) {
        if(accountNo != -1){
            // Implementation logic for net banking
            // Return true if successful, false otherwise
            return true;
        }
        return false;
    }
}



class PaymentManager {
    public boolean makePayment(User user, float amount) {
        if (user.isPayFromWallet()) {
            if (user.isWalletAutoDeduct()) {
                return deductFromWallet(user, amount);
            } else {
                return deductFromWallet(user, amount);
            }
        } else {
            if (user.getUserType() == UserType.STUDENT) {
                return addToFee(user, amount);
            } else {
                return deductFromSalary(user, amount);
            }
        }
    }

    private boolean deductFromSalary(User user, float amount) {
        // interacts with another system to deduct from salary
        return false;
    }

    private boolean addToFee(User user, float amount) {
        // interacts with another system to add to fee
        return true;
    }

    private boolean deductFromWallet(User user, float amount) {
        user.getWallet().deductMoney(amount);
        return false;
    }
}
interface PaymentScheme {
    void makeScheme(float x, float y, float z);
}

class QRCodeScanner {
    public SmartVehicle scanQRCode(String vehicleQRCode) {
        // The database will query the vehicleQRCode and return the vehicle
        SmartVehicle vehicle = new SmartVehicle(SmartVehicleType.BIKE, vehicleQRCode, 0);
        return vehicle;
    }
}

class Rating {
    private int RatingId;
    private float rating;
    private String comment;

    public Rating(int ratingId, float rating, String comment) {
        this.RatingId = ratingId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getRatingId() {
        return RatingId;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setRatingId(int ratingId) {
        this.RatingId = ratingId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

class RegistrationManager {
    private boolean validateId(ID id) {
        // Validate that ID is not expired and is valid
        if(id.getExpiry().compareTo(new Date()) < 0) {
            return false;
        }
        return true;
    }

    public boolean register(String email, String password, String name, Address address, ID id) {
        // Register the user using email, password and ID
        if(!validateId(id)) {
            return false;
        }
        return false;
    }
}

class SmartBook {
  private RegistrationManager registrationManager;
  private TripManager tripManager;
  private List<DockingStation> dockingStations;
  private List<User> users;
  private List<SmartVehicle> vehicles;
  private PaymentManager paymentManager;
  private List<SupportDocument> supportDocuments;
  private List<Feedback> feedbacks;

  public SmartBook() {
    this.registrationManager = new RegistrationManager();
    this.tripManager = new TripManager();
    this.dockingStations = null;
    this.users = null;
    this.vehicles = null;
    this.paymentManager = new PaymentManager();
    this.supportDocuments = null;
    this.feedbacks = null;
  }

  public User getUserbyEmail(String email) {
    for (User user : users) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }
    return null;
  }

  public boolean login(String email, String password) {
    User user = getUserbyEmail(email);
    if (user == null) {
      return false;
    }
    if (user.getPassword().equals(password)) {
      // login
      return true;
    }
    return false;
  }

  public boolean logout() {
    // logout
    return true;
  }

  public boolean addFeedback(Feedback feedback) {
    feedbacks.add(feedback);
    return true;
  }

  // getter and setter functions
  public RegistrationManager getRegistrationManager() {
    return registrationManager;
  }

  public void setRegistrationManager(RegistrationManager registrationManager) {
    this.registrationManager = registrationManager;
  }

  public TripManager getTripManager() {
    return tripManager;
  }

  public void setTripManager(TripManager tripManager) {
    this.tripManager = tripManager;
  }

  public List<DockingStation> getDockingStations() {
    return dockingStations;
  }

  public void setDockingStations(List<DockingStation> dockingStations) {
    this.dockingStations = dockingStations;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<SmartVehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<SmartVehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public PaymentManager getPaymentManager() {
    return paymentManager;
  }

  public void setPaymentManager(PaymentManager paymentManager) {
    this.paymentManager = paymentManager;
  }

  public List<SupportDocument> getSupportDocuments() {
    return supportDocuments;
  }

  public void setSupportDocuments(List<SupportDocument> supportDocuments) {
    this.supportDocuments = supportDocuments;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }
}

class SmartVehicle implements PaymentScheme{
    private String vehicleId;
    private SmartVehicleType vehicleType;
    private String registrationNumber;
    private int dockingStationId; // -1 if not docked
    private Condition condition;
    private String qrCode;
    private SupportDocument supportDocument;
    private GPS location;
    private Map<Date, String> maintainenceLog;
    private float x;
    private float y;
    private float z;

    public SmartVehicle(SmartVehicleType vehicleType, String registrationNumber, int dockingStationId) {
        this.vehicleId = "-1"; // Must be unique id
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
        this.dockingStationId = dockingStationId;
    }

    public float getx() {
        return x;
    }

    public void setx(float x) {
        this.x = x;
    }

    public float gety() {
        return y;
    }

    public void sety(float y) {
        this.y = y;
    }

    public float getz() {
        return z;
    }

    public void setz(float z) {
        this.z = z;
    }

    public void makeScheme(float x, float y, float z) {
        // Implementation
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean checkInUse() {
        if(dockingStationId == -1)
            return true;
        return false;
    }

    public void logMaintenance(Date time, String maintenanceType) {
        // Logs any maintenance event for the vehicle
    }

    public String getVehicleID() {
        return vehicleId;
    }

    public SmartVehicleType getType() {
        return vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getDockingStationId() {
        return dockingStationId;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getQrCode() {
        return qrCode;
    }

    public SupportDocument getSupportDocument() {
        return supportDocument;
    }

    public Map<Date, String> getMaintainenceLog() {
        return maintainenceLog;
    }

    public void setDockingStationId(int dockingStationId) {
        this.dockingStationId = dockingStationId;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setSupportDocument(SupportDocument supportDocument) {
        this.supportDocument = supportDocument;
    }

    public void setMaintainenceLog(Map<Date, String> maintainenceLog) {
        this.maintainenceLog = maintainenceLog;
    }

    public GPS getLocation() {
        return location;
    }

    public void setLocation(GPS location) {
        this.location = location;
    }

    public boolean checkCondition() {
        // Checks the condition of the vehicle
        if (condition.getEngineStatus() == ConditionType.BAD || condition.getBrakes() == ConditionType.BAD || condition.getLights() == ConditionType.BAD) {
            return false;
        }
        return true;
    }
}

class SupportDocument{
    private int documentId;
    private File document;

    public SupportDocument(int documentId, File document) {
        this.documentId = documentId;
        this.document = document;
    }

    public int getDocumentId() {
        return documentId;
    }

    public File getDocument() {
        return document;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public void setDocument(File document) {
        this.document = document;
    }
}

class Tracker {
    public Date startTracking() {
        return new Date();
    }

    public Date stopTracking() {
        return new Date();
    }

    public float getDistanceTravelled() {
        // Get the distance travelled by the vehicle using GPS
        return 0.0f;
    }
}
