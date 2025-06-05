package com.variada.services;

import static com.variada.utils.enums.EnumConstantes.TRUE;

import com.sura.service.creacionSiniestro.gen.Author;
import com.sura.service.creacionSiniestro.gen.CPLine;
import com.sura.service.creacionSiniestro.gen.Claimant;
import com.sura.service.creacionSiniestro.gen.ClaimsRequest;
import com.sura.service.creacionSiniestro.gen.FixedPropertyIncident;
import com.sura.service.creacionSiniestro.gen.Lobs;
import com.sura.service.creacionSiniestro.gen.LossEstimate;
import com.sura.service.creacionSiniestro.gen.LossLocation;
import com.sura.service.creacionSiniestro.gen.MainContact;
import com.sura.service.creacionSiniestro.gen.Params;
import com.sura.service.creacionSiniestro.gen.PrimaryAddress;
import com.sura.service.creacionSiniestro.gen.PrimaryAddress_;
import com.sura.service.creacionSiniestro.gen.Property;
import com.sura.service.creacionSiniestro.gen.PropertyContentsIncident;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreacionSiniestroFactory {

  private static final String ID_SERVICIO_CLAIM = "6";
  private static final String METHOD_CREATE_CLAIM = "createClaim";
  private static final String LOSS_TYPE_EMPRESARIAL = "PR";
  private static final String JSONRPC_2 = "2.0";
  private static final String DESCRIPCION = "Justificación valor total reclamado";

  private String policyNumber;
  private String lossDate;
  private String notificationDate;
  private String lossCause;
  private String policySystemId;
  private String descriptionLoss;
  private String description;
  private Boolean isPolicyProperty;
  private String stateProperty;
  private String addressLine1Property;
  private String cityProperty;
  private String documentTypeAnts;
  private String contactNameAnts;
  private String taxIdAnts;
  private String emailAddress1Ants;
  private String cellNumberAnt;
  private String stateAnt;
  private String addressLine1Ant;
  private String cityAnt;
  private String propertyDesc;
  private String cellNumberMainContact;
  private String emailAddress1MainContact;
  private String documentTypeMainContact;
  private String taxIdMainContact;
  private String stateMainContact;
  private String addressLine1MainContact;
  private String cityMainContact;
  private String contactNameMainContact;
  private String addressLine1LossLocation;
  private String cityLossLocation;
  private String stateLossLocation;
  private int amountLossEstimate;
  private String currencyLossEstimate;
  private String authorUser;
  private String documentTypeAuthor;
  private String taxIdAuthor;
  private String nameAuthor;
  private boolean fixedPropertyIncident;
  private boolean propertyContentsIncident;

  public ClaimsRequest creacionSiniestroRequestFactory() {
    ClaimsRequest crearSiniestroRequest = new ClaimsRequest();
    crearSiniestroRequest.setId(ID_SERVICIO_CLAIM);
    crearSiniestroRequest.setMethod(METHOD_CREATE_CLAIM);
    crearSiniestroRequest.setParams(listParamFactory());
    crearSiniestroRequest.setJsonrpc(JSONRPC_2);
    return crearSiniestroRequest;
  }

  private List<Object> listParamFactory() {
    List<Object> listParams = new ArrayList<>();
    Params param = paramFactory();
    listParams.add(policyNumber);
    listParams.add(param);
    return listParams;
  }

  private Params paramFactory() {
    Params param = new Params();
    param.setLossDate(getLossDate());
    param.setNotificationDate(getNotificationDate());
    param.setLossType(LOSS_TYPE_EMPRESARIAL);
    param.setLossCause(getLossCause());
    param.setLobs(lobsFactory());
    param.setMainContact(mainContactFactory());
    param.setLossLocation(lossLocationFactory());
    param.setDescription(getDescriptionLoss());
    param.setLossEstimate(lossEstimateFactory());
    param.setAuthorUser(getAuthorUser());
    param.setAuthor(authorFactory());
    return param;
  }

  private Lobs lobsFactory() {
    Lobs lobs = new Lobs();
    lobs.setCPLine(cpLineFactory());
    return lobs;
  }

  private CPLine cpLineFactory() {
    CPLine cpLine = new CPLine();
    cpLine.setPolicySystemId(getPolicySystemId());
    if (fixedPropertyIncident == Boolean.parseBoolean(TRUE.getValor())) {
      cpLine.setFixedPropertyIncident(listFixedPropertyIncidentFactory());
    }
    if (propertyContentsIncident == Boolean.parseBoolean(TRUE.getValor())) {
      cpLine.setPropertyContentsIncident(listPropertyContentsIncidentFactory());
    }
    return cpLine;
  }

  private List<FixedPropertyIncident> listFixedPropertyIncidentFactory() {
    List<FixedPropertyIncident> listFixedPropertyIncident = new ArrayList<>();
    FixedPropertyIncident fixedPropertyIncidentFac = fixedPropertyIncidentFactory();
    listFixedPropertyIncident.add(fixedPropertyIncidentFac);
    return listFixedPropertyIncident;
  }

  private FixedPropertyIncident fixedPropertyIncidentFactory() {
    FixedPropertyIncident fixedPropertyIncidentFac = new FixedPropertyIncident();
    fixedPropertyIncidentFac.setDescription(DESCRIPCION);
    fixedPropertyIncidentFac.setIsPolicyProperty(true);
    fixedPropertyIncidentFac.setProperty(propertyFactory());
    fixedPropertyIncidentFac.setClaimant(claimantFactory());
    fixedPropertyIncidentFac.setPropertyDesc(DESCRIPCION);
    return fixedPropertyIncidentFac;
  }

  private List<PropertyContentsIncident> listPropertyContentsIncidentFactory() {
    List<PropertyContentsIncident> listPropertyContentsIncident = new ArrayList<>();
    PropertyContentsIncident propertyContentsIncidentFac = propertyContentsIncident();
    listPropertyContentsIncident.add(propertyContentsIncidentFac);
    return listPropertyContentsIncident;
  }

  private PropertyContentsIncident propertyContentsIncident() {
    PropertyContentsIncident propertyContentsIncidentFac = new PropertyContentsIncident();
    propertyContentsIncidentFac.setDescription(DESCRIPCION);
    return propertyContentsIncidentFac;
  }

  private Property propertyFactory() {
    Property property = new Property();
    property.setState(getStateProperty());
    property.setAddressLine1(getAddressLine1Property());
    property.setCity(getCityProperty());
    return property;
  }

  private Claimant claimantFactory() {
    Claimant claimant = new Claimant();
    claimant.setDocumentType(getDocumentTypeAnts());
    claimant.setContactName(getContactNameAnts());
    claimant.setTaxID(getTaxIdAnts());
    claimant.setEmailAddress1(getEmailAddress1Ants());
    claimant.setCellNumber(getCellNumberAnt());
    claimant.setPrimaryAddress(primaryAddressFactoryAnt());
    return claimant;
  }

  private PrimaryAddress_ primaryAddressFactoryAnt() {
    PrimaryAddress_ primaryAddressAnt = new PrimaryAddress_();
    primaryAddressAnt.setState(getStateAnt());
    primaryAddressAnt.setAddressLine1(getAddressLine1Ant());
    primaryAddressAnt.setCity(getCityAnt());
    return primaryAddressAnt;
  }

  private MainContact mainContactFactory() {
    MainContact mainContact = new MainContact();
    mainContact.setDocumentType(getDocumentTypeMainContact());
    mainContact.setContactName(getContactNameMainContact());
    mainContact.setTaxID(getTaxIdMainContact());
    mainContact.setEmailAddress1(getEmailAddress1MainContact());
    mainContact.setCellNumber(getCellNumberMainContact());
    mainContact.setPrimaryAddress(primaryAddressFactoryMainContact());
    return mainContact;
  }

  private PrimaryAddress primaryAddressFactoryMainContact() {
    PrimaryAddress primaryAddress = new PrimaryAddress();
    primaryAddress.setState(getStateMainContact());
    primaryAddress.setAddressLine1(getAddressLine1MainContact());
    primaryAddress.setCity(getCityMainContact());
    return primaryAddress;
  }

  private LossLocation lossLocationFactory() {
    LossLocation lossLocation = new LossLocation();
    lossLocation.setState(getStateLossLocation());
    lossLocation.setAddressLine1(getAddressLine1LossLocation());
    lossLocation.setCity(getCityLossLocation());
    return lossLocation;
  }

  private LossEstimate lossEstimateFactory() {
    LossEstimate lossEstimate = new LossEstimate();
    lossEstimate.setAmount(getAmountLossEstimate());
    lossEstimate.setCurrency(getCurrencyLossEstimate());
    return lossEstimate;
  }

  private Author authorFactory() {
    Author author = new Author();
    author.setDocumentType(getDocumentTypeAuthor());
    author.setTaxID(getTaxIdAuthor());
    author.setName(getNameAuthor());
    return author;
  }
}
