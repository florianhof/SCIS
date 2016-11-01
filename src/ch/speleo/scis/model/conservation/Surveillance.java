package ch.speleo.scis.model.conservation;

import static ch.speleo.scis.persistence.typemapping.CodedEnumType.TYPE;

import java.io.Serializable;
import java.util.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.openxava.annotations.*;
import org.openxava.util.Labels;

import ch.speleo.scis.model.common.*;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;
import ch.speleo.scis.persistence.utils.SimpleQueries;

/**
 * Class representing a surveillance sheet using Hibernate annotation
 * 
 * @author Julien Debache
 * @version 1.0
 */
@Entity
@Table(name = "SURVEILLANCE",
	uniqueConstraints =
		@UniqueConstraint(columnNames = "ID"))
@Audited
@Tab(properties = "dateCreated,"
		+ "description,"
		+ "visit,"
		+ "legalSituation,"
		+ "initials,"
		+ "evaluationDate,"
		+ "geotopeInventory,"
		+ "importance,"
		+ "protection,"
		+ "protectionType,"
		+ "estheticImportanceRating,"
		+ "estheticImportance,"
		+ "culturalImportanceRating,"
		+ "culturalImportance,"
		+ "historicalImportanceRating,"
		+ "historicalImportance,"
		+ "biospeleoImportanceRating,"
		+ "biospeleoImportance,"
		+ "volumeImportanceRating,"
		+ "volumeImportance,"
		+ "paleontologicalImportanceRating,"
		+ "paleontologicalImportance,"
		+ "archaeologicalImportanceRating,"
		+ "archaeologicalImportance,"
		+ "geologicalImportanceRating,"
		+ "geologicalImportance,"
		+ "hydroImportanceRating,"
		+ "hydroImportance,"
		+ "hydrogeoImportanceRating,"
		+ "hydrogeoImportance,"
		+ "geomorphologicalImportanceRating,"
		+ "geomorphologicalImportance,"
		+ "riskImportanceRating,"
		+ "riskImportance,"
		,
	rowStyles = {@RowStyle(style="deletedData", property="deleted", value="true")})
@Views({
	@View(name = "Short", members = "id, dateCreated, initials"), 
	@View(members = "dateCreated; description; visit; legalSituation; initials; evaluationDate; geotopeInventory; importance; protection; protectionType")
})
public class Surveillance
extends GenericIdentity implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 5654131353056129922L;
    
    /**
     * TODO : fetch id from karst object instead
     */
    
    /**
     * Creation date of the sheet
     */
    @Required
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private Date dateCreated;
    
    /**
     * Description of heritage
     */
    @Column(name = "DESCRIPTION", nullable = true, updatable = true, length=10000)
	@DisplaySize(value=30, forViews="Short")
    private String description;
    
    /**
     * Contact address for visits
     */
    @Embedded
    private AddressVisit visit;
    
    /**
     * Legal standing
     */
    @Column(name = "LEGAL_SITUATION", nullable = true, length=200)
    private String legalSituation;
    
    /**
     * Initials of the object
     */
    @Column(name = "INITIALS", nullable = true, length=3)
    private String initials;
    
    /**
     * Date of evaluation (what is an evaluation ?)
     */
    @Column(name = "EVALUATION_DATE", nullable = true)
    private Date evaluationDate;
    
    /**
     * Is it part of the inventoried geotope
     */
    @Column(name = "GEOTOPE_INVENTORY", nullable = false)
    private Boolean geotopeInventory;
    
    /**
     * Choose one for importance : national, regional, local
     */
    @Column(name = "IMPORTANCE", nullable = true, length = 25)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=ImportanceEnum.CLASSNAME)})
    private ImportanceEnum importance;
    
    /**
     * Other protection no/yes
     */
    @Column(name = "PROTECTION", nullable = false)
    private Boolean protection;
    
    /**
     * Type of protection
     */
    @Column(name = "PROTECTION_TYPE", nullable = true, length=200)
    private String protectionType;
    
    /**
     * Rating of esthetic importance
     */
    @Column(name = "ESTHETIC_IMPORTANCE_RATING", nullable = true, length=1)
    private int estheticImportanceRating;
    
    /**
     * Description of noticeable stuff from an esthetic point of view
     */
    @Column(name = "ESTHETIC_IMPORTANCE", nullable = true, length=100)
    private String estheticImportance;
    
    /**
     * Rating of cultural importance
     */
    @Column(name = "CULTURAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int culturalImportanceRating;
    
    /**
     * Description of noticeable stuff from a cultural point of view
     */
    @Column(name = "CULTURAL_IMPORTANCE", nullable = true, length=100)
    private String culturalImportance;
    
    /**
     * Rating of historical importance
     */
    @Column(name = "HISTORICAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int historicalImportanceRating;
    
    /**
     * Description of noticeable stuff from an historical point of view
     */
    @Column(name = "HISTORICAL_IMPORTANCE", nullable = true, length=100)
    private String historicalImportance;
    
    /**
     * Rating of biospeleological importance
     */
    @Column(name = "BIOSPELEOLOGICAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int biospeleoImportanceRating;
    
    /**
     * Description of noticeable stuff from a biospeleological point of view
     */
    @Column(name = "BIOSPELEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String biospeleoImportance;
    
    /**
     * Rating of volume
     */
    @Column(name = "VOLUME_IMPORTANCE_RATING", nullable = true, length=1)
    private int volumeImportanceRating;
    
    /**
     * Description of stuff of notice from a volumic / size point of view
     */
    @Column(name = "VOLUME_IMPORTANCE", nullable = true, length=100)
    private String volumeImportance;
    
    /**
     * Rating of paleoontological importance
     */
    @Column(name = "PALEONTOLOGIC_IMPORTANCE_RATING", nullable = true, length=1)
    private int paleontologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a paleontological point of view
     */
    @Column(name = "PALEONTOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String paleontologicalImportance;
    
    /**
     * Rating of archaeological importance
     */
    @Column(name = "ARCHAEOLOGICAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int archaeologicalImportanceRating;
    
    /**
     * Description of stuff of notice from an archaeological point of view
     */
    @Column(name = "ARCHAEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String archaeologicalImportance;
    
    /**
     * Rating of geological importance
     */
    @Column(name = "GEOLOGICAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int geologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a geological point of view
     */
    @Column(name = "GEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String geologicalImportance;
    
    /**
     * Rating of hydrological importance
     */
    @Column(name = "HYDRO_IMPORTANCE_RATING", nullable = true, length=1)
    private int hydroImportanceRating;
    
    /**
     * Description of stuff of notice from an hydrological point of view
     */
    @Column(name = "HYDRO_IMPORTANCE", nullable = true, length=100)
    private String hydroImportance;
    
    /**
     * Rating of hydrogeological importance
     */
    @Column(name = "HYDROGEO_IMPORTANCE_RATING", nullable = true, length=1)
    private int hydrogeoImportanceRating;
    
    /**
     * Description of stuff of notice from an hydrogeological point of view
     */
    @Column(name = "HYDROGEO_IMPORTANCE", nullable = true, length=100)
    private String hydrogeoImportance;
    
    /**
     * Rating of geomorphological importance
     */
    @Column(name = "GEOMORPHOLOGICAL_IMPORTANCE_RATING", nullable = true, length=1)
    private int geomorphologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a geomorphological point of view
     */
    @Column(name = "GEOMORPHOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String geomorphologicalImportance;
    
    /**
     * Rating of risk importance
     */
    @Column(name = "RISK_IMPORTANCE_RATING", nullable = true, length=1)
    private int riskImportanceRating;
    
    /**
     * Description of the stuff of notice from a risk point of view
     */
    @Column(name = "RISK_IMPORTANCE", nullable = true, length=1)
    private String riskImportance;

    /**
     * Empty constructor
     */
    public Surveillance() { }
    
    /**
     * Getters and setters (automatically generated by Eclipse)
     */
    
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressVisit getVisit() {
		return visit;
	}

	public void setVisit(AddressVisit visit) {
		this.visit = visit;
	}

	public String getLegalSituation() {
		return legalSituation;
	}

	public void setLegalSituation(String legalSituation) {
		this.legalSituation = legalSituation;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public Boolean getGeotopeInventory() {
		return geotopeInventory;
	}

	public void setGeotopeInventory(Boolean geotopeInventory) {
		this.geotopeInventory = geotopeInventory;
	}

	public ImportanceEnum getImportance() {
		return importance;
	}

	public void setImportance(ImportanceEnum importance) {
		this.importance = importance;
	}

	public Boolean getProtection() {
		return protection;
	}

	public void setProtection(Boolean protection) {
		this.protection = protection;
	}

	public String getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(String protectionType) {
		this.protectionType = protectionType;
	}

	public int getEstheticRating() {
		return estheticImportanceRating;
	}

	public void setEstheticRating(int estheticRating) {
		this.estheticImportanceRating = estheticRating;
	}

	public String getEsthetic() {
		return estheticImportance;
	}

	public void setEsthetic(String esthetic) {
		this.estheticImportance = esthetic;
	}

	public int getCulturalImportanceRating() {
		return culturalImportanceRating;
	}

	public void setCulturalImportanceRating(int culturalImportanceRating) {
		this.culturalImportanceRating = culturalImportanceRating;
	}

	public String getCulturalImportance() {
		return culturalImportance;
	}

	public void setCulturalImportance(String culturalImportance) {
		this.culturalImportance = culturalImportance;
	}

	public int getHistoricalImportanceRating() {
		return historicalImportanceRating;
	}

	public void setHistoricalImportanceRating(int historicalImportanceRating) {
		this.historicalImportanceRating = historicalImportanceRating;
	}

	public String getHistoricalImportance() {
		return historicalImportance;
	}

	public void setHistoricalImportance(String historicalImportance) {
		this.historicalImportance = historicalImportance;
	}

	public int getBiospeleoImportanceRating() {
		return biospeleoImportanceRating;
	}

	public void setBiospeleoImportanceRating(int biospeleoImportanceRating) {
		this.biospeleoImportanceRating = biospeleoImportanceRating;
	}

	public String getBiospeleoImportance() {
		return biospeleoImportance;
	}

	public void setBiospeleoImportance(String biospeleoImportance) {
		this.biospeleoImportance = biospeleoImportance;
	}

	public int getVolumeImportanceRating() {
		return volumeImportanceRating;
	}

	public void setVolumeImportanceRating(int volumeImportanceRating) {
		this.volumeImportanceRating = volumeImportanceRating;
	}

	public String getVolumeImportance() {
		return volumeImportance;
	}

	public void setVolumeImportance(String volumeImportance) {
		this.volumeImportance = volumeImportance;
	}

	public int getPaleontologicalImportanceRating() {
		return paleontologicalImportanceRating;
	}

	public void setPaleontologicalImportanceRating(int paleontologicalImportanceRating) {
		this.paleontologicalImportanceRating = paleontologicalImportanceRating;
	}

	public String getPaleontologicImportance() {
		return paleontologicalImportance;
	}

	public void setPaleontologicImportance(String paleontologicImportance) {
		this.paleontologicalImportance = paleontologicImportance;
	}

	public int getArchaeologicalImportanceRating() {
		return archaeologicalImportanceRating;
	}

	public void setArchaeologicalImportanceRating(int archaeologicalImportanceRating) {
		this.archaeologicalImportanceRating = archaeologicalImportanceRating;
	}

	public String getArchaeologicalImportance() {
		return archaeologicalImportance;
	}

	public void setArchaeologicalImportance(String archaeologicalImportance) {
		this.archaeologicalImportance = archaeologicalImportance;
	}

	public int getGeologicalImportanceRating() {
		return geologicalImportanceRating;
	}

	public void setGeologicalImportanceRating(int geologicalImportanceRating) {
		this.geologicalImportanceRating = geologicalImportanceRating;
	}

	public String getGeologicalImportance() {
		return geologicalImportance;
	}

	public void setGeologicalImportance(String geologicalImportance) {
		this.geologicalImportance = geologicalImportance;
	}

	public int getHydroImportanceRating() {
		return hydroImportanceRating;
	}

	public void setHydroImportanceRating(int hydroImportanceRating) {
		this.hydroImportanceRating = hydroImportanceRating;
	}

	public String getHydroImportance() {
		return hydroImportance;
	}

	public void setHydroImportance(String hydroImportance) {
		this.hydroImportance = hydroImportance;
	}

	public int getHydrogeoImportanceRating() {
		return hydrogeoImportanceRating;
	}

	public void setHydrogeoImportanceRating(int hydrogeoImportanceRating) {
		this.hydrogeoImportanceRating = hydrogeoImportanceRating;
	}

	public String getHydrogeoImportance() {
		return hydrogeoImportance;
	}

	public void setHydrogeoImportance(String hydrogeoImportance) {
		this.hydrogeoImportance = hydrogeoImportance;
	}

	public int getGeomorphologicalImportanceRating() {
		return geomorphologicalImportanceRating;
	}

	public void setGeomorphologicalImportanceRating(int geomorphologicalImportanceRating) {
		this.geomorphologicalImportanceRating = geomorphologicalImportanceRating;
	}

	public String getGeomorphologicalImportance() {
		return geomorphologicalImportance;
	}

	public void setGeomorphologicalImportance(String geomorphologicalImportance) {
		this.geomorphologicalImportance = geomorphologicalImportance;
	}

	public int getRiskImportanceRating() {
		return riskImportanceRating;
	}

	public void setRiskImportanceRating(int riskImportanceRating) {
		this.riskImportanceRating = riskImportanceRating;
	}

	public String getRiskImportance() {
		return riskImportance;
	}

	public void setRiskImportance(String riskImportance) {
		this.riskImportance = riskImportance;
	}
    
	
    @Override
	protected void writeFields(StringBuilder builder) {
		super.writeFields(builder);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", description=");
		builder.append(description);
		builder.append(", visit=");
		builder.append(visit);
		builder.append(", legalSituation=");
		builder.append(legalSituation);
		builder.append(", initials=");
		builder.append(initials);
		builder.append(", evaluationDate=");
		builder.append(evaluationDate);
		builder.append(", geotopeInventory=");
		builder.append(geotopeInventory);
		builder.append(", importance=");
		builder.append(importance);
		builder.append(", protection=");
		builder.append(protection);
		builder.append(", protectionType=");
		builder.append(protectionType);
		builder.append(", estheticImportanceRating=");
		builder.append(estheticImportanceRating);
		builder.append(", estheticImportance=");
		builder.append(estheticImportance);
		builder.append(", culturalImportanceRating=");
		builder.append(culturalImportanceRating);
		builder.append(", culturalImportance=");
		builder.append(culturalImportance);
		builder.append(", historicalImportanceRating=");
		builder.append(historicalImportanceRating);
		builder.append(", historicalImportance=");
		builder.append(historicalImportance);
		builder.append(", biospeleoImportanceRating=");
		builder.append(biospeleoImportanceRating);
		builder.append(", biospeleoImportance=");
		builder.append(biospeleoImportance);
		builder.append(", volumeImportanceRating=");
		builder.append(volumeImportanceRating);
		builder.append(", volumeImportance=");
		builder.append(volumeImportance);
		builder.append(", paleontologicalImportanceRating=");
		builder.append(paleontologicalImportanceRating);
		builder.append(", paleontologicalImportance=");
		builder.append(paleontologicalImportance);
		builder.append(", archaeologicalImportanceRating=");
		builder.append(archaeologicalImportanceRating);
		builder.append(", archaeologicalImportance=");
		builder.append(archaeologicalImportance);
		builder.append(", geologicalImportanceRating=");
		builder.append(geologicalImportanceRating);
		builder.append(", geologicalImportance=");
		builder.append(geologicalImportance);
		builder.append(", hydroImportanceRating=");
		builder.append(hydroImportanceRating);
		builder.append(", hydroImportance=");
		builder.append(hydroImportance);
		builder.append(", hydrogeoImportanceRating=");
		builder.append(hydrogeoImportanceRating);
		builder.append(", hydrogeoImportance=");
		builder.append(hydrogeoImportance);
		builder.append(", geomorphologicalImportanceRating=");
		builder.append(geomorphologicalImportanceRating);
		builder.append(", geomorphologicalImportance=");
		builder.append(geomorphologicalImportance);
		builder.append(", riskImportanceRating=");
		builder.append(riskImportanceRating);
		builder.append(", riskImportance=");
		builder.append(riskImportance);
		
	}
}