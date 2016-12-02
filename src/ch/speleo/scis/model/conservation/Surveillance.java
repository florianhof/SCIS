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

import org.openxava.calculators.*;

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
		+ "legalSituation,"
		+ "initials,"
		+ "evaluationDate,"
		+ "importance,"
		+ "protection,"
		+ "protectionType,"
		+ "estheticImportanceRating,"
		+ "culturalImportanceRating,"
		+ "historicalImportanceRating,"
		+ "biospeleoImportanceRating,"
		+ "volumeImportanceRating,"
		+ "paleontologicalImportanceRating,"
		+ "archaeologicalImportanceRating,"
		+ "geologicalImportanceRating,"
		+ "hydroImportanceRating,"
		+ "hydrogeoImportanceRating,"
		+ "geomorphologicalImportanceRating,"
		+ "riskImportanceRating,"
		+ "accessibility,"
		+ "visitorFrequency,"
		,
	rowStyles = {@RowStyle(style="deletedData", property="deleted", value="true")})
@Views({
	@View(name = "Short", members = "dateCreated, initials"), 
	@View(members = "dateCreated;"
			+ "description;"
			+ "address;"
			+ "legalSituation;"
			+ "initials;"
			+ "evaluationDate;"
			+ "importance;"
			+ "protection,"
			+ "protectionType;"
			+ "importance [estheticImportanceRating, estheticImportance;"
			+ "historicalImportanceRating, historicalImportance;"
			+ "culturalImportanceRating, culturalImportance;"
			+ "biospeleoImportanceRating, biospeleoImportance;"
			+ "volumeImportanceRating, volumeImportance;"
			+ "paleontologicalImportanceRating, paleontologicalImportance;"
			+ "archaeologicalImportanceRating, archaeologicalImportance;"
			+ "geologicalImportanceRating, geologicalImportance;"
			+ "hydroImportanceRating, hydroImportance;"
			+ "hydrogeoImportanceRating, hydrogeoImportance;"
			+ "geomorphologicalImportanceRating, geomorphologicalImportance];"
			+ "visitors [visitorFrequency, visitorType;"
			+ "accessibility, riskImportanceRating, riskImportance]")
})
public class Surveillance
extends GenericIdentity implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 5654131353056129922L;
    
    /**
     * Creation date of the sheet
     */
    @Required
    @Temporal(TemporalType.DATE)
    @DefaultValueCalculator(CurrentDateCalculator.class)
    private Date dateCreated;
    
    /**
     * Description of heritage
     */
    @Column(name = "DESCRIPTION", nullable = true, updatable = true, length=1000)
	@DisplaySize(value=30, forViews="Short")
    private String description;
    
    /**
     * Contact address for visits
     */
    @Embedded
    private PostalAddress address;
    
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
    @Temporal(TemporalType.DATE)
    @Column(name = "EVALUATION_DATE", nullable = true)
    private Date evaluationDate;
    
    /**
     * Choose one for importance : national, regional, local
     */
    @Column(name = "IMPORTANCE", nullable = true, length = 1)
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
    @Column(name = "ESTHETIC_IMPORTANCE_RATING", nullable = true, length = 25)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum estheticImportanceRating;
    
    /**
     * Description of noticeable stuff from an esthetic point of view
     */
    @Column(name = "ESTHETIC_IMPORTANCE", nullable = true, length=100)
    private String estheticImportance;
    
    /**
     * Rating of cultural importance
     */
    @Column(name = "CULTURAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum culturalImportanceRating;
    
    /**
     * Description of noticeable stuff from a cultural point of view
     */
    @Column(name = "CULTURAL_IMPORTANCE", nullable = true, length=100)
    private String culturalImportance;
    
    /**
     * Rating of historical importance
     */
    @Column(name = "HISTORICAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum historicalImportanceRating;
    
    /**
     * Description of noticeable stuff from an historical point of view
     */
    @Column(name = "HISTORICAL_IMPORTANCE", nullable = true, length=100)
    private String historicalImportance;
    
    /**
     * Rating of biospeleological importance
     */
    @Column(name = "BIOSPELEOLOGICAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum biospeleoImportanceRating;
    
    /**
     * Description of noticeable stuff from a biospeleological point of view
     */
    @Column(name = "BIOSPELEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String biospeleoImportance;
    
    /**
     * Rating of volume
     */
    @Column(name = "VOLUME_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum volumeImportanceRating;
    
    /**
     * Description of stuff of notice from a volumic / size point of view
     */
    @Column(name = "VOLUME_IMPORTANCE", nullable = true, length=100)
    private String volumeImportance;
    
    /**
     * Rating of paleoontological importance
     */
    @Column(name = "PALEONTOLOGIC_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum paleontologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a paleontological point of view
     */
    @Column(name = "PALEONTOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String paleontologicalImportance;
    
    /**
     * Rating of archaeological importance
     */
    @Column(name = "ARCHAEOLOGICAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum archaeologicalImportanceRating;
    
    /**
     * Description of stuff of notice from an archaeological point of view
     */
    @Column(name = "ARCHAEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String archaeologicalImportance;
    
    /**
     * Rating of geological importance
     */
    @Column(name = "GEOLOGICAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum geologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a geological point of view
     */
    @Column(name = "GEOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String geologicalImportance;
    
    /**
     * Rating of hydrological importance
     */
    @Column(name = "HYDRO_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum hydroImportanceRating;
    
    /**
     * Description of stuff of notice from an hydrological point of view
     */
    @Column(name = "HYDRO_IMPORTANCE", nullable = true, length=100)
    private String hydroImportance;
    
    /**
     * Rating of hydrogeological importance
     */
    @Column(name = "HYDROGEO_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum hydrogeoImportanceRating;
    
    /**
     * Description of stuff of notice from an hydrogeological point of view
     */
    @Column(name = "HYDROGEO_IMPORTANCE", nullable = true, length=100)
    private String hydrogeoImportance;
    
    /**
     * Rating of geomorphological importance
     */
    @Column(name = "GEOMORPHOLOGICAL_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum geomorphologicalImportanceRating;
    
    /**
     * Description of stuff of notice from a geomorphological point of view
     */
    @Column(name = "GEOMORPHOLOGICAL_IMPORTANCE", nullable = true, length=100)
    private String geomorphologicalImportance;
    
    /**
     * Rating of risk importance
     */
    @Column(name = "RISK_IMPORTANCE_RATING", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum riskImportanceRating;
    
    /**
     * Description of the stuff of notice from a risk point of view
     */
    @Column(name = "RISK_IMPORTANCE", nullable = true, length=100)
    private String riskImportance;
    
    /**
     * Rating of accessibility
     */
    @Column(name = "ACCESSIBILITY", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=DifficultyEnum.CLASSNAME)})
    private DifficultyEnum accessibility;
    
    /**
     * Rating of visitor frequency
     */
    @Column(name = "VISITOR_FREQUENCY", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME,
    parameters={ @Parameter(name=TYPE, value=FrequencyEnum.CLASSNAME)})
    private FrequencyEnum visitorFrequency;
    
    /**
     * Description of visitor type
     */
    @Column(name = "VISITOR_TYPE", nullable = true, length = 100)
    private String visitorType;

    /**
     * Empty constructor
     */
    public Surveillance() { }
    
    /**
     * Getters and setters (automatically generated by Eclipse)
     */
    public String getStreet() {
    	return this.address.getStreet();
    }
    
    public void setStreet(String street) {
    	this.address.setStreet(street);
    }
    
    public int getPostalCode() {
    	return this.address.getPostalCode();
    }
    
    public void setPostalCode(int postalCode) {
    	this.address.setPostalCode(postalCode);
    }
    
    public String getCity() {
    	return this.address.getCity();
    }
    
    public void setCity(String city) {
    	this.address.setCity(city);
    }
    
    public String getCanton() {
    	return this.address.getCanton();
    }
    
    public void setCanton(String canton) {
    	this.address.setCanton(canton);
    }
    
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

	public PostalAddress getAddress() {
		return address;
	}

	public void setAddress(PostalAddress address) {
		this.address = address;
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

	public RatingEnum getEstheticImportanceRating() {
		return estheticImportanceRating;
	}

	public void setEstheticImportanceRating(RatingEnum estheticImportanceRating) {
		this.estheticImportanceRating = estheticImportanceRating;
	}

	public String getEstheticImportance() {
		return estheticImportance;
	}

	public void setEstheticImportance(String estheticImportance) {
		this.estheticImportance = estheticImportance;
	}

	public RatingEnum getCulturalImportanceRating() {
		return culturalImportanceRating;
	}

	public void setCulturalImportanceRating(RatingEnum culturalImportanceRating) {
		this.culturalImportanceRating = culturalImportanceRating;
	}

	public String getCulturalImportance() {
		return culturalImportance;
	}

	public void setCulturalImportance(String culturalImportance) {
		this.culturalImportance = culturalImportance;
	}

	public RatingEnum getHistoricalImportanceRating() {
		return historicalImportanceRating;
	}

	public void setHistoricalImportanceRating(RatingEnum historicalImportanceRating) {
		this.historicalImportanceRating = historicalImportanceRating;
	}

	public String getHistoricalImportance() {
		return historicalImportance;
	}

	public void setHistoricalImportance(String historicalImportance) {
		this.historicalImportance = historicalImportance;
	}

	public RatingEnum getBiospeleoImportanceRating() {
		return biospeleoImportanceRating;
	}

	public void setBiospeleoImportanceRating(RatingEnum biospeleoImportanceRating) {
		this.biospeleoImportanceRating = biospeleoImportanceRating;
	}

	public String getBiospeleoImportance() {
		return biospeleoImportance;
	}

	public void setBiospeleoImportance(String biospeleoImportance) {
		this.biospeleoImportance = biospeleoImportance;
	}

	public RatingEnum getVolumeImportanceRating() {
		return volumeImportanceRating;
	}

	public void setVolumeImportanceRating(RatingEnum volumeImportanceRating) {
		this.volumeImportanceRating = volumeImportanceRating;
	}

	public String getVolumeImportance() {
		return volumeImportance;
	}

	public void setVolumeImportance(String volumeImportance) {
		this.volumeImportance = volumeImportance;
	}

	public RatingEnum getPaleontologicalImportanceRating() {
		return paleontologicalImportanceRating;
	}

	public void setPaleontologicalImportanceRating(RatingEnum paleontologicalImportanceRating) {
		this.paleontologicalImportanceRating = paleontologicalImportanceRating;
	}

	public String getPaleontologicalImportance() {
		return paleontologicalImportance;
	}

	public void setPaleontologicalImportance(String paleontologicalImportance) {
		this.paleontologicalImportance = paleontologicalImportance;
	}

	public RatingEnum getArchaeologicalImportanceRating() {
		return archaeologicalImportanceRating;
	}

	public void setArchaeologicalImportanceRating(RatingEnum archaeologicalImportanceRating) {
		this.archaeologicalImportanceRating = archaeologicalImportanceRating;
	}

	public String getArchaeologicalImportance() {
		return archaeologicalImportance;
	}

	public void setArchaeologicalImportance(String archaeologicalImportance) {
		this.archaeologicalImportance = archaeologicalImportance;
	}

	public RatingEnum getGeologicalImportanceRating() {
		return geologicalImportanceRating;
	}

	public void setGeologicalImportanceRating(RatingEnum geologicalImportanceRating) {
		this.geologicalImportanceRating = geologicalImportanceRating;
	}

	public String getGeologicalImportance() {
		return geologicalImportance;
	}

	public void setGeologicalImportance(String geologicalImportance) {
		this.geologicalImportance = geologicalImportance;
	}

	public RatingEnum getHydroImportanceRating() {
		return hydroImportanceRating;
	}

	public void setHydroImportanceRating(RatingEnum hydroImportanceRating) {
		this.hydroImportanceRating = hydroImportanceRating;
	}

	public String getHydroImportance() {
		return hydroImportance;
	}

	public void setHydroImportance(String hydroImportance) {
		this.hydroImportance = hydroImportance;
	}

	public RatingEnum getHydrogeoImportanceRating() {
		return hydrogeoImportanceRating;
	}

	public void setHydrogeoImportanceRating(RatingEnum hydrogeoImportanceRating) {
		this.hydrogeoImportanceRating = hydrogeoImportanceRating;
	}

	public String getHydrogeoImportance() {
		return hydrogeoImportance;
	}

	public void setHydrogeoImportance(String hydrogeoImportance) {
		this.hydrogeoImportance = hydrogeoImportance;
	}

	public RatingEnum getGeomorphologicalImportanceRating() {
		return geomorphologicalImportanceRating;
	}

	public void setGeomorphologicalImportanceRating(RatingEnum geomorphologicalImportanceRating) {
		this.geomorphologicalImportanceRating = geomorphologicalImportanceRating;
	}

	public String getGeomorphologicalImportance() {
		return geomorphologicalImportance;
	}

	public void setGeomorphologicalImportance(String geomorphologicalImportance) {
		this.geomorphologicalImportance = geomorphologicalImportance;
	}

	public RatingEnum getRiskImportanceRating() {
		return riskImportanceRating;
	}

	public void setRiskImportanceRating(RatingEnum riskImportanceRating) {
		this.riskImportanceRating = riskImportanceRating;
	}

	public String getRiskImportance() {
		return riskImportance;
	}

	public void setRiskImportance(String riskImportance) {
		this.riskImportance = riskImportance;
	}
	
	public DifficultyEnum getAccessibility() {
		return accessibility;
	}
	
	public void setAccessibility(DifficultyEnum accessibility) {
		this.accessibility = accessibility;
	}
	
	public FrequencyEnum getVisitorFrequency() {
		return visitorFrequency;
	}
	
	public void setVisitorFrequency(FrequencyEnum visitorFrequency) {
		this.visitorFrequency = visitorFrequency;
	}
	
	public String getVisitorType() {
		return visitorType;
	}
	
	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}
	
    @Override
	protected void writeFields(StringBuilder builder) {
		super.writeFields(builder);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", description=");
		builder.append(description);
		builder.append(", street=");
		builder.append(getStreet());
		builder.append(", postalCode=");
		builder.append(getPostalCode());
		builder.append(", city=");
		builder.append(getCity());
		builder.append(", canton=");
		builder.append(getCanton());
		builder.append(", legalSituation=");
		builder.append(legalSituation);
		builder.append(", initials=");
		builder.append(initials);
		builder.append(", evaluationDate=");
		builder.append(evaluationDate);
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
		builder.append(", accessibility=");
		builder.append(accessibility);
		builder.append(", visitorFrequency=");
		builder.append(visitorFrequency);
		builder.append(", visitorType=");
		builder.append(visitorType);
		
	}
}