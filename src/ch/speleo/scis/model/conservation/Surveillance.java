package ch.speleo.scis.model.conservation;

import static ch.speleo.scis.persistence.typemapping.CodedEnumType.TYPE;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DisplaySize;
import org.openxava.annotations.EntityValidator;
import org.openxava.annotations.LabelFormat;
import org.openxava.annotations.LabelFormatType;
import org.openxava.annotations.ListAction;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.PropertyValue;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Required;
import org.openxava.annotations.RowStyle;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;

import ch.speleo.scis.business.utils.ConditionalRequiredValidator;
import ch.speleo.scis.business.utils.CurrentUserNickNameOrIdCalculator;
import ch.speleo.scis.model.common.GenericIdentityWithRevision;
import ch.speleo.scis.model.common.Identifiable;
import ch.speleo.scis.model.karst.KarstObject;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;

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
@Tab(properties = ""
		+ "creationDate,"
		+ "contact,"
		+ "object.name, "
		+ "significance,"
		+ "protectied,"
		+ "visitorFrequency,"
		+ "visitorType,"
		+ "accessibility,"
		,
	rowStyles = {@RowStyle(style="deletedData", property="deleted", value="true")})
@Views({
	@View(name = "Short", members = "creationDate, contact; object"), 
	@View(members = ""
			+ "object; "
			+ "creationDate, contact;"
			+ "description;"
			+ "significance, protectied, protectionInfo;"
			+ "hint;"
			+ "importance ["
			+ "  estheticImportance, estheticRemark;"
			+ "  historicalImportance, historicalRemark;"
			+ "  biospeleoImportance, biospeleoRemark;"
			+ "  volumeImportance, volumeRemark;"
			+ "  paleontologicalImportance, paleontologicalRemark;"
			+ "  geologicalImportance, geologicalRemark;"
			+ "  hydrogeoImportance, hydrogeoRemark;"
			+ "  geomorphologicalImportance, geomorphologicalRemark;"
			+ "];"
			+ "visitors ["
			+ "  visitorFrequency, visitorType;"
			+ "  accessibility, dangerType, dangerRemark"
			+ "];"
			+ "visits"),
	@View(name=GenericIdentityWithRevision.AUDIT_VIEW_NAME, members = " auditedValues")
})
@EntityValidator(value=ConditionalRequiredValidator.class, properties={
	@PropertyValue(name="required", from="protectied"),
	@PropertyValue(name="value", from="protectionInfo"),
	@PropertyValue(name="conditionName", value="protectied"),
	@PropertyValue(name="valueName", value="protectionInfo")
})
public class Surveillance
extends GenericIdentityWithRevision implements Serializable, Identifiable {

    private static final long serialVersionUID = 5654131353056129922L;
    
    private static final int IMPORTANCES_REMARK_LENGTH = 500;
    
    @ManyToOne
    @JoinColumn(name="OBJECT_ID", nullable = true)
    @ReferenceView(value = "Short")
    private KarstObject object;
    
    @Column(name = "CREATION_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    @DefaultValueCalculator(CurrentDateCalculator.class)
    @Required @ReadOnly
    private Date creationDate;
    
    @Column(name = "CONTACT")
    @DefaultValueCalculator(CurrentUserNickNameOrIdCalculator.class)
    private String contact;
    
    @Column(name = "DESCRIPTION", nullable = true, length=50000)
    @Stereotype("BIGTEXT_AREA")
    private String description;
    
    @Column(name = "IMPORTANCE", nullable = true, length = 1)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=SignificanceEnum.CLASSNAME)})
    private SignificanceEnum significance;
    
    @Column(name = "PROTECTED")
    private Boolean protectied;
    
    @Column(name = "PROTECTION_INFO", length=50000)
	@DisplaySize(value=50)
    private String protectionInfo;
    
    @Column(name = "HINT", length=50000)
	@DisplaySize(value=100)
    private String hint;
    
    @Column(name = "ESTHETIC_IMPORTANCE", nullable = true, length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum estheticImportance;
    
    @Column(name = "ESTHETIC_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String estheticRemark;
    
	@Column(name = "HISTORICAL_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
	@Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
	private RatingEnum historicalImportance;

	@Column(name = "HISTORICAL_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
	private String historicalRemark;

    @Column(name = "BIOSPELEOLOGICAL_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum biospeleoImportance;
    
    @Column(name = "BIOSPELEOLOGICAL_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String biospeleoRemark;
    
    @Column(name = "VOLUME_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum volumeImportance;
    
    @Column(name = "VOLUME_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String volumeRemark;
    
    @Column(name = "PALEONTOLOGIC_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum paleontologicalImportance;
    
    @Column(name = "PALEONTOLOGICAL_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String paleontologicalRemark;
    
    @Column(name = "GEOLOGICAL_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum geologicalImportance;
    
    @Column(name = "GEOLOGICAL_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String geologicalRemark;
    
    @Column(name = "HYDROGEO_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum hydrogeoImportance;
    
    @Column(name = "HYDROGEO_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String hydrogeoRemark;
    
    @Column(name = "GEOMORPHOLOGICAL_IMPORTANCE", length = RatingEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=RatingEnum.CLASSNAME)})
    private RatingEnum geomorphologicalImportance;
    
    @Column(name = "GEOMORPHOLOGICAL_REMARK", length=IMPORTANCES_REMARK_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String geomorphologicalRemark;
    
	@Column(name = "VISITOR_FREQUENCY", length = FrequencyEnum.CODE_LENGTH)
	@Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=FrequencyEnum.CLASSNAME)})
	private FrequencyEnum visitorFrequency;

	@Column(name = "VISITOR_TYPE", nullable = false, length = VisitorTypeEnum.CODE_LENGTH)
	@Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=VisitorTypeEnum.CLASSNAME)})
    @Required
	private VisitorTypeEnum visitorType;

	@Column(name = "ACCESSIBILITY", nullable = true, length = DifficultyEnum.CODE_LENGTH)
	@Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=DifficultyEnum.CLASSNAME)})
    @Required
	private DifficultyEnum accessibility;

    @Column(name = "DANGER_TYPE", nullable = false, length = DangerTypeEnum.CODE_LENGTH)
    @Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=DangerTypeEnum.CLASSNAME)})
    @Required
    private DangerTypeEnum dangerType;
    
    @Column(name = "DANGER_REMARK", nullable = true, length=IMPORTANCES_REMARK_LENGTH)
    private String dangerRemark;
    
    @OneToMany(mappedBy = "surveillance", 
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @ListProperties(value = "visitDate, visitor, status, statusRemark, measureRequired, measureRemark")
    @OrderBy("visitDate")
    @AsEmbedded
    @ListAction("CollectionScis.add")
    private Collection<Visit> visits = new LinkedList<Visit>();

    public KarstObject getObject() {
		return object;
	}

	public void setObject(KarstObject object) {
		this.object = object;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SignificanceEnum getSignificance() {
		return significance;
	}

	public void setSignificance(SignificanceEnum significance) {
		this.significance = significance;
	}

	public Boolean getProtectied() {
		return protectied;
	}

	public void setProtectied(Boolean protectied) {
		this.protectied = protectied;
	}

	public String getProtectionInfo() {
		return protectionInfo;
	}

	public void setProtectionInfo(String protectionInfo) {
		this.protectionInfo = protectionInfo;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public RatingEnum getEstheticImportance() {
		return estheticImportance;
	}

	public void setEstheticImportance(RatingEnum estheticImportance) {
		this.estheticImportance = estheticImportance;
	}

	public String getEstheticRemark() {
		return estheticRemark;
	}

	public void setEstheticRemark(String estheticRemark) {
		this.estheticRemark = estheticRemark;
	}

	public RatingEnum getHistoricalImportance() {
		return historicalImportance;
	}

	public void setHistoricalImportance(RatingEnum historicalImportance) {
		this.historicalImportance = historicalImportance;
	}

	public String getHistoricalRemark() {
		return historicalRemark;
	}

	public void setHistoricalRemark(String historicalRemark) {
		this.historicalRemark = historicalRemark;
	}

	public RatingEnum getBiospeleoImportance() {
		return biospeleoImportance;
	}

	public void setBiospeleoImportance(RatingEnum biospeleoImportance) {
		this.biospeleoImportance = biospeleoImportance;
	}

	public String getBiospeleoRemark() {
		return biospeleoRemark;
	}

	public void setBiospeleoRemark(String biospeleoRemark) {
		this.biospeleoRemark = biospeleoRemark;
	}

	public RatingEnum getVolumeImportance() {
		return volumeImportance;
	}

	public void setVolumeImportance(RatingEnum volumeImportance) {
		this.volumeImportance = volumeImportance;
	}

	public String getVolumeRemark() {
		return volumeRemark;
	}

	public void setVolumeRemark(String volumeRemark) {
		this.volumeRemark = volumeRemark;
	}

	public RatingEnum getPaleontologicalImportance() {
		return paleontologicalImportance;
	}

	public void setPaleontologicalImportance(RatingEnum paleontologicalImportance) {
		this.paleontologicalImportance = paleontologicalImportance;
	}

	public String getPaleontologicalRemark() {
		return paleontologicalRemark;
	}

	public void setPaleontologicalRemark(String paleontologicalRemark) {
		this.paleontologicalRemark = paleontologicalRemark;
	}

	public RatingEnum getGeologicalImportance() {
		return geologicalImportance;
	}

	public void setGeologicalImportance(RatingEnum geologicalImportance) {
		this.geologicalImportance = geologicalImportance;
	}

	public String getGeologicalRemark() {
		return geologicalRemark;
	}

	public void setGeologicalRemark(String geologicalRemark) {
		this.geologicalRemark = geologicalRemark;
	}

	public RatingEnum getHydrogeoImportance() {
		return hydrogeoImportance;
	}

	public void setHydrogeoImportance(RatingEnum hydrogeoImportance) {
		this.hydrogeoImportance = hydrogeoImportance;
	}

	public String getHydrogeoRemark() {
		return hydrogeoRemark;
	}

	public void setHydrogeoRemark(String hydrogeoRemark) {
		this.hydrogeoRemark = hydrogeoRemark;
	}

	public RatingEnum getGeomorphologicalImportance() {
		return geomorphologicalImportance;
	}

	public void setGeomorphologicalImportance(RatingEnum geomorphologicalImportance) {
		this.geomorphologicalImportance = geomorphologicalImportance;
	}

	public String getGeomorphologicalRemark() {
		return geomorphologicalRemark;
	}

	public void setGeomorphologicalRemark(String geomorphologicalRemark) {
		this.geomorphologicalRemark = geomorphologicalRemark;
	}

	public FrequencyEnum getVisitorFrequency() {
		return visitorFrequency;
	}

	public void setVisitorFrequency(FrequencyEnum visitorFrequency) {
		this.visitorFrequency = visitorFrequency;
	}

	public VisitorTypeEnum getVisitorType() {
		return visitorType;
	}

	public void setVisitorType(VisitorTypeEnum visitorType) {
		this.visitorType = visitorType;
	}

	public DifficultyEnum getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(DifficultyEnum accessibility) {
		this.accessibility = accessibility;
	}

	public DangerTypeEnum getDangerType() {
		return dangerType;
	}

	public void setDangerType(DangerTypeEnum dangerType) {
		this.dangerType = dangerType;
	}

	public String getDangerRemark() {
		return dangerRemark;
	}

	public void setDangerRemark(String dangerRemark) {
		this.dangerRemark = dangerRemark;
	}

	public Collection<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Collection<Visit> visits) {
		this.visits = visits;
	}

	public String getBusinessId() {
		return (object != null) ? object.getBusinessId() : null;
	}

    @ListProperties("revision.modificationDate, revision.username, object.name, contact, description, significance, protectied, protectionInfo, hint, " +
    		"estheticImportance, estheticRemark, historicalImportance, historicalRemark, biospeleoImportance, biospeleoRemark, " +
    		"volumeImportance, volumeRemark, paleontologicalImportance, paleontologicalRemark, geologicalImportance, geologicalRemark, " + 
    		"hydrogeoImportance, hydrogeoRemark, geomorphologicalImportance, geomorphologicalRemark, " +
    		"visitorFrequency, visitorType, accessibility, dangerType, dangerRemark")
    @ReadOnly
    public Collection<Surveillance> getAuditedValues() {
    	return loadAuditedValues();
    }

	@Override
	protected void writeFields(StringBuilder builder) {
		super.writeFields(builder);
		builder.append(", object=");
		builder.append(object);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", description=");
		builder.append(description);
		builder.append(", importance=");
		builder.append(significance);
		builder.append(", protection=");
		builder.append(protectied);
		builder.append(", protectionType=");
		builder.append(protectionInfo);
		builder.append(", visitorFrequency=");
		builder.append(visitorFrequency);
		builder.append(", visitorType=");
		builder.append(visitorType);
		builder.append(", accessibility=");
		builder.append(accessibility);
		builder.append(", dangerRemark=");
		builder.append(dangerType);
		builder.append(", dangerRemark=");
		builder.append(dangerRemark);		
	}
    
}
