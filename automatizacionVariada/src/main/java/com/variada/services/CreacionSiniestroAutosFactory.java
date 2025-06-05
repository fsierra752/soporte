package com.variada.services;

import com.sura.service.creacionSiniestro.gen.Author;
import com.sura.service.creacionSiniestro.gen.BodyPartDetail;
import com.sura.service.creacionSiniestro.gen.ClaimsRequest;
import com.sura.service.creacionSiniestro.gen.Driver;
import com.sura.service.creacionSiniestro.gen.Injured;
import com.sura.service.creacionSiniestro.gen.InjuryIncident;
import com.sura.service.creacionSiniestro.gen.Lobs;
import com.sura.service.creacionSiniestro.gen.LossEstimate;
import com.sura.service.creacionSiniestro.gen.LossLocation;
import com.sura.service.creacionSiniestro.gen.MainContact;
import com.sura.service.creacionSiniestro.gen.Params;
import com.sura.service.creacionSiniestro.gen.PersonalAuto;
import com.sura.service.creacionSiniestro.gen.PrimaryAddress;
import com.sura.service.creacionSiniestro.gen.PrimaryAddress_;
import com.sura.service.creacionSiniestro.gen.PrimaryAddress__;
import com.sura.service.creacionSiniestro.gen.Vehicle;
import com.sura.service.creacionSiniestro.gen.VehicleIncident;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class CreacionSiniestroAutosFactory {

  private static final String ID_SERVICIO_CLAIM = "6";
  private static final String METHOD_CREATE_CLAIM = "createClaim";
  private static final String JSONRPC_2 = "2.0";

  private String policyNumber;
  private String description;
  private String notificationDate;
  private String lossType;
  private String lossDate;
  private String authorUser;
  private String lossCause;
  private String macaNumber;
  private String faultRating;
  private String originCause;
  private String segment;
  private String authorityTransit;
  private String documentType;
  private String taxIDAuthor;
  private int amountLossEstimate;
  private String currencyLossEstimate;
  private String documentTypeMainContact;
  private String taxIDMainContact;
  private String emailAddress1MainContact;
  private String cellNumberMainContact;
  private String firstNameMainContact;
  private String middleNameMainContact;
  private String lastNameMainContact;
  private String secondLastNameMainContact;
  private String workNumberMainContact;
  private String policyRoleMainContact;
  private String addressLine1MainContact;
  private String addressTypeMainContact;
  private String cityMainContact;
  private String descriptionVehicleIncident;
  private String repairShopVehicleIncident;
  private String lossPartyVehicleIncident;
  private String driverRelationVehicleIncident;
  private String firstNameDriver;
  private String middleNameDriver;
  private String lastNameDriver;
  private String secondLastNameDriver;
  private String workNumberDriver;
  private String cellNumberDriver;
  private String emailAddress1Driver;
  private String policyRoleDriver;
  private String documentTypeDriver;
  private String taxIDDriver;
  private String addressLine1Driver;
  private String addressTypeDriver;
  private String cityDriver;
  private String licensePlateVehicle;
  private String makeVehicle;
  private String modelVehicle;
  private String engineNumberVehicle;
  private int yearVehicle;
  private String colorVehicle;
  private String vehicleType;
  private String fasecoldaCode;
  private String vinVehicle;
  private String lossPartyInjuryIncident;
  private String severityInjuryIncident;
  private String descriptionInjuryIncident;
  private String generalInjuryType;
  private String detailedInjuryType;
  private String firstNameInjured;
  private String middleNameInjured;
  private String lastNameInjured;
  private String secondLastNameInjured;
  private String workNumberInjured;
  private String cellNumberInjured;
  private String emailAddress1Injured;
  private String documentTypeInjured;
  private String taxIDInjured;
  private String primaryBodyPart1;
  private String detailedBodyPartType1;
  private String primaryBodyPart2;
  private String detailedBodyPartType2;
  private String addressLine1Injured;
  private String addressTypeInjured;
  private String cityInjured;
  private String countryLossLocation;
  private String addressLine1LossLocation;
  private String cityLossLocation;
  private String documentTypeAnt;
  private String contactNameAnt;
  private String taxIdAnt;
  private String emailAddress1Ant;
  private String cellNumberAnt;
  private Boolean isSuspect;
  private String suspectDesc;


  public ClaimsRequest creacionSiniestroAutoRequestFactory() {
    ClaimsRequest crearSiniestroAutoRequest = new ClaimsRequest();
    crearSiniestroAutoRequest.setId(ID_SERVICIO_CLAIM);
    crearSiniestroAutoRequest.setMethod(METHOD_CREATE_CLAIM);
    crearSiniestroAutoRequest.setParams(listParamFactory());
    crearSiniestroAutoRequest.setJsonrpc(JSONRPC_2);
    return crearSiniestroAutoRequest;
  }

  private List<Object> listParamFactory() {
    List<Object> listParams = new ArrayList();
    Params parametro = paramAutoFactory();
    listParams.add(getPolicyNumber());
    listParams.add(parametro);
    return listParams;
  }

  private Params paramAutoFactory() {
    Params parametro = new Params();
    parametro.setLossDate(getLossDate());
    parametro.setNotificationDate(getNotificationDate());
    parametro.setLossType(getLossType());
    parametro.setLossCause(getLossCause());
    parametro.setLobs(lobsFactory());
    parametro.setMainContact(mainContanctFactory());
    parametro.setLossLocation(lossLocationFactory());
    parametro.setDescription(getDescription());
    parametro.setMacaNumber(getMacaNumber());
    parametro.setFaultRating(getFaultRating());
    parametro.setLossEstimate(lossEstimateFactory());
    parametro.setAuthorUser(getAuthorUser());
    parametro.setIsSuspect(getIsSuspect());
    parametro.setSuspectDesc(getSuspectDesc());
    parametro.setOriginCause(getOriginCause());
    parametro.setSegment(getSegment());
    parametro.setAuthor(authorFactory());
    parametro.setAuthorityTransit(getAuthorityTransit());
    return parametro;
  }

  private Author authorFactory() {
    Author author = new Author();
    author.setDocumentType(getDocumentType());
    author.setTaxID(getTaxIDAuthor());
    return author;
  }

  private LossEstimate lossEstimateFactory() {
    LossEstimate lossEstimate = new LossEstimate();
    lossEstimate.setAmount(getAmountLossEstimate());
    lossEstimate.setCurrency(getCurrencyLossEstimate());
    return lossEstimate;
  }

  private MainContact mainContanctFactory() {
    MainContact mainContact = new MainContact();
    mainContact.setDocumentType(getDocumentTypeMainContact());
    mainContact.setTaxID(getTaxIDMainContact());
    mainContact.setEmailAddress1(getEmailAddress1MainContact());
    mainContact.setCellNumber(getCellNumberMainContact());
    mainContact.setPrimaryAddress(primaryAddressFactory());
    mainContact.setFirstName(getFirstNameMainContact());
    mainContact.setMiddleName(getMiddleNameMainContact());
    mainContact.setLastName(getLastNameMainContact());
    mainContact.setSecondLastName(getSecondLastNameMainContact());
    mainContact.setWorkNumber(getWorkNumberMainContact());
    mainContact.setPolicyRole(getPolicyRoleMainContact());

    return mainContact;
  }

  private PrimaryAddress__ primaryAddressFactoryDos() {
    PrimaryAddress__ primaryAddressDos = new PrimaryAddress__();
    primaryAddressDos.setAddressLine1(getAddressLine1MainContact());
    primaryAddressDos.setAddressType(getAddressTypeMainContact());
    primaryAddressDos.setCity(getCityMainContact());
    return primaryAddressDos;
  }

  private Lobs lobsFactory() {
    Lobs lobs = new Lobs();
    lobs.setPersonalAuto(personalAutoFactory());
    return lobs;
  }

  private PersonalAuto personalAutoFactory() {
    PersonalAuto personalAuto = new PersonalAuto();
    personalAuto.setVehicleIncidents(listVehicleIncidentsFactory());
    personalAuto.setInjuryIncident(listInjuryIncidentFactory());
    personalAuto.setFixedPropertyIncident(Collections.emptyList());
    return personalAuto;
  }

  private List<VehicleIncident> listVehicleIncidentsFactory() {
    List<VehicleIncident> listVehicleIncident = new ArrayList<>();
    VehicleIncident vehicleIncident = vehicleIncidentFactory();
    listVehicleIncident.add(vehicleIncident);
    return listVehicleIncident;
  }

  private VehicleIncident vehicleIncidentFactory() {
    VehicleIncident vehicleIncident = new VehicleIncident();
    vehicleIncident.setDescription(getDescriptionVehicleIncident());
    vehicleIncident.setRepairShop(getRepairShopVehicleIncident());
    vehicleIncident.setLossParty(getLossPartyVehicleIncident());
    vehicleIncident.setMovePermission(false);
    vehicleIncident.setCollision(true);
    vehicleIncident.setDriverRelation(getDriverRelationVehicleIncident());
    vehicleIncident.setDriver(driverFactory());
    vehicleIncident.setVehicle(vehicleFactory());
    vehicleIncident.setPassengers(Collections.emptyList());
    return vehicleIncident;
  }

  private Driver driverFactory() {
    Driver driver = new Driver();
    driver.setFirstName(getFirstNameDriver());
    driver.setMiddleName(getMiddleNameDriver());
    driver.setLastName(getLastNameDriver());
    driver.setSecondLastName(getSecondLastNameDriver());
    driver.setWorkNumber(getWorkNumberDriver());
    driver.setCellNumber(getCellNumberDriver());
    driver.setEmailAddress1(getEmailAddress1Driver());
    driver.setPolicyRole(getPolicyRoleDriver());
    driver.setDocumentType(getDocumentTypeDriver());
    driver.setTaxID(getTaxIDDriver());
    driver.setPrimaryAddress(primaryAddressFactoryDos());
    return driver;
  }

  private PrimaryAddress primaryAddressFactory() {
    PrimaryAddress primaryAddress = new PrimaryAddress();
    primaryAddress.setAddressLine1(getAddressLine1Driver());
    primaryAddress.setAddressType(getAddressTypeDriver());
    primaryAddress.setCity(getCityDriver());
    return primaryAddress;
  }

  private Vehicle vehicleFactory() {
    Vehicle vehicle = new Vehicle();
    vehicle.setLicensePlate(getLicensePlateVehicle());
    vehicle.setMake(getMakeVehicle());
    vehicle.setModel(getModelVehicle());
    vehicle.setEngineNumber(getEngineNumberVehicle());
    vehicle.setYear(getYearVehicle());
    vehicle.setColor(getColorVehicle());
    vehicle.setVehicleType(getVehicleType());
    vehicle.setFasecoldaCode(getFasecoldaCode());
    vehicle.setVin(getVinVehicle());
    return vehicle;
  }

  private List<InjuryIncident> listInjuryIncidentFactory() {
    List<InjuryIncident> listInjuryIncident = new ArrayList<>();
    InjuryIncident injuryIncident = injuryIncidentFactory();
    listInjuryIncident.add(injuryIncident);
    return listInjuryIncident;
  }

  private InjuryIncident injuryIncidentFactory() {
    InjuryIncident injuryIncident = new InjuryIncident();
    injuryIncident.setLossParty(getLossPartyInjuryIncident());
    injuryIncident.setInjured(injuredFactory());
    injuryIncident.setSeverity(getSeverityInjuryIncident());
    injuryIncident.setDescription(getDescriptionInjuryIncident());
    injuryIncident.setGeneralInjuryType(getGeneralInjuryType());
    injuryIncident.setDetailedInjuryType(getDetailedInjuryType());
    injuryIncident.setBodyPartDetails(listBodyPartDetailFactory());
    return injuryIncident;
  }

  private Injured injuredFactory() {
    Injured injured = new Injured();
    injured.setFirstName(getFirstNameInjured());
    injured.setMiddleName(getMiddleNameInjured());
    injured.setLastName(getLastNameInjured());
    injured.setSecondLastName(getSecondLastNameInjured());
    injured.setWorkNumber(getWorkNumberInjured());
    injured.setCellNumber(getCellNumberInjured());
    injured.setEmailAddress1(getEmailAddress1Injured());
    injured.setDocumentType(getDocumentTypeInjured());
    injured.setTaxID(getTaxIDInjured());
    injured.setPrimaryAddress(primaryAddressFactoryUno());
    return injured;
  }

  private List<BodyPartDetail> listBodyPartDetailFactory() {
    List<BodyPartDetail> listBodyPartDetail = new ArrayList<>();
    listBodyPartDetail.add(bodyPartDetail1Factory());
    listBodyPartDetail.add(bodyPartDetail2Factory());
    return listBodyPartDetail;
  }

  private BodyPartDetail bodyPartDetail1Factory() {
    BodyPartDetail bodyPartDetail = new BodyPartDetail();
    bodyPartDetail.setPrimaryBodyPart(getPrimaryBodyPart1());
    bodyPartDetail.setDetailedBodyPartType(getDetailedBodyPartType1());
    return bodyPartDetail;
  }

  private BodyPartDetail bodyPartDetail2Factory() {
    BodyPartDetail bodyPartDetail = new BodyPartDetail();
    bodyPartDetail.setPrimaryBodyPart(getPrimaryBodyPart2());
    bodyPartDetail.setDetailedBodyPartType(getDetailedBodyPartType2());
    return bodyPartDetail;
  }

  private PrimaryAddress_ primaryAddressFactoryUno() {
    PrimaryAddress_ primaryAddressUno = new PrimaryAddress_();
    primaryAddressUno.setAddressLine1(getAddressLine1Injured());
    primaryAddressUno.setAddressType(getAddressTypeInjured());
    primaryAddressUno.setCity(getCityInjured());
    return primaryAddressUno;
  }

  private LossLocation lossLocationFactory() {
    LossLocation lossLocation = new LossLocation();
    lossLocation.setCountry(getCountryLossLocation());
    lossLocation.setAddressLine1(getAddressLine1LossLocation());
    lossLocation.setCity(getCityLossLocation());
    return lossLocation;
  }
}
