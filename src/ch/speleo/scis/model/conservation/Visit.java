package ch.speleo.scis.model.conservation;

import static ch.speleo.scis.persistence.typemapping.CodedEnumType.TYPE;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.LabelFormat;
import org.openxava.annotations.LabelFormatType;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Required;
import org.openxava.annotations.RowStyle;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;

import ch.speleo.scis.business.utils.CurrentUserNickNameOrIdCalculator;
import ch.speleo.scis.model.common.GenericIdentityWithRevision;
import ch.speleo.scis.model.common.Identifiable;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;

/**
 * Class modeling a visit of a karst object
 * 
 * @author Julien
 * @version 1.0
 */
@Entity
@Table(name = "VISIT",
	uniqueConstraints =
		@UniqueConstraint(columnNames = "ID"))
@Audited
@Tab(properties = ""
		+ "visitDate,"
		+ "visitor,"
		+ "surveillance.object.name,"
		+ "status,"
		+ "wasteExists,"
		+ "scribblingsExists,"
		+ "damagesExists,"
		+ "measureRequired, measureRemark"
		, 
	rowStyles = {@RowStyle(style="deletedData", property="deleted", value="true")})
@Views({
	@View(name = "Short", members = "visitDate, visitor; surveillance.object"),
	@View(members = ""
			+ "surveillance;"
			+ "visitDate, visitor;"
			+ "status, statusRemark;"
			+ "depredations [wasteExists, wasteRemark; scribblingsExists, scribblingsRemark; damagesExists, damagesRemark];"
			+ "photosExist, photograph;"
			+ "measureRequired, measureRemark;"
			+ "comment"),
	@View(name=GenericIdentityWithRevision.AUDIT_VIEW_NAME, members = " auditedValues")
})
public class Visit
extends GenericIdentityWithRevision implements Serializable, Identifiable {

    private static final long serialVersionUID = 5654131353056129922L;
    
    private static final int REMARKS_LENGTH = 500;

    @ManyToOne
    @JoinColumn(name="SURVEILLANCE_ID", nullable = false)
    @ReferenceView(value = "Short")
    @Required
    private Surveillance surveillance;
    
    @Column(name = "VISIT_DATE")
    @Temporal(TemporalType.DATE)
    @DefaultValueCalculator(CurrentDateCalculator.class)
    @Required
    private Date visitDate;
    
    @Column(name = "VISITOR", length=50)
    @DefaultValueCalculator(CurrentUserNickNameOrIdCalculator.class)
    private String visitor;
    
	@Column(name = "STATUS", nullable = false, length = ConservationStatusEnum.CODE_LENGTH)
	@Type(type=CodedEnumType.CLASSNAME, parameters={ @Parameter(name=TYPE, value=ConservationStatusEnum.CLASSNAME)})
    @Required
    private ConservationStatusEnum status;
    
    @Column(name = "STATUS_REMARK")
    private String statusRemark;
    
    @Column(name = "WASTE_EXISTS")
    private boolean wasteExists;
    
    @Column(name = "WASTE_REMARK", length = REMARKS_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String wasteRemark;
    
    @Column(name = "SCRIBBLINGS_EXISTS")
    private boolean scribblingsExists;
    
    @Column(name = "SCRIBBLINGS_REMARK", length = REMARKS_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String scribblingsRemark;
    
    @Column(name = "DAMAGES_EXISTS")
    private boolean damagesExists;
    
    @Column(name = "DAMAGES_REMARK", length = REMARKS_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String damagesRemark;
    
    @Column(name = "PHOTOS_EXIST")
    private boolean photosExist;
    
    @Column(name = "PHOTOGRAPH", length=50)
    private String photograph;
    
    @Column(name = "MEASURE_REQUIRED")
    private boolean measureRequired;
    
    @Column(name = "MEASURE", length=REMARKS_LENGTH)
    @LabelFormat(value=LabelFormatType.NO_LABEL, forViews="DEFAULT")
    private String measureRemark;
    
    @Column(name = "COMMENT", length=50000)
    @Stereotype("BIGTEXT_AREA")
    private String comment;

	public Surveillance getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(Surveillance surveillance) {
		this.surveillance = surveillance;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public ConservationStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ConservationStatusEnum status) {
		this.status = status;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public boolean isWasteExists() {
		return wasteExists;
	}

	public void setWasteExists(boolean wasteExists) {
		this.wasteExists = wasteExists;
	}

	public String getWasteRemark() {
		return wasteRemark;
	}

	public void setWasteRemark(String wasteRemark) {
		this.wasteRemark = wasteRemark;
	}

	public boolean isScribblingsExists() {
		return scribblingsExists;
	}

	public void setScribblingsExists(boolean scribblingsExists) {
		this.scribblingsExists = scribblingsExists;
	}

	public String getScribblingsRemark() {
		return scribblingsRemark;
	}

	public void setScribblingsRemark(String scribblingsRemark) {
		this.scribblingsRemark = scribblingsRemark;
	}

	public boolean isDamagesExists() {
		return damagesExists;
	}

	public void setDamagesExists(boolean damagesExists) {
		this.damagesExists = damagesExists;
	}

	public String getDamagesRemark() {
		return damagesRemark;
	}

	public void setDamagesRemark(String damagesRemark) {
		this.damagesRemark = damagesRemark;
	}

	public boolean getPhotosExist() {
		return photosExist;
	}

	public void setPhotosExist(boolean photos) {
		this.photosExist = photos;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	public boolean isMeasureRequired() {
		return measureRequired;
	}

	public void setMeasureRequired(boolean measureRequired) {
		this.measureRequired = measureRequired;
	}

	public String getMeasureRemark() {
		return measureRemark;
	}

	public void setMeasureRemark(String measureRemark) {
		this.measureRemark = measureRemark;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBusinessId() {
		StringBuilder builder = new StringBuilder();
		if (visitDate != null) builder.append(new SimpleDateFormat("yyyy-MM-dd").format(visitDate)).append(" ");
		builder.append(visitor);
		builder.append(" @ ");
		builder.append(surveillance.getBusinessId());
		return builder.toString();
	}

    @ListProperties("revision.modificationDate, revision.username, surveillance.object.name, visitDate, visitor, "
    		+ "status, statusRemark, wasteExists, wasteRemark, scribblingsExists, scribblingsRemark, damagesExists, damagesRemark, "
    		+ "photos, photograph, measureRequired, measureRemark, comment")
    @ReadOnly
    public Collection<Visit> getAuditedValues() {
    	return loadAuditedValues();
    }

	@Override
	protected void writeFields(StringBuilder builder) {
		super.writeFields(builder);
		builder.append(", surveillance=");
		builder.append(surveillance==null? "<null>" : surveillance.getBusinessId());
		builder.append(", visitDate=");
		builder.append(visitDate);
		builder.append(", visitDate=");
		builder.append(visitor);
		builder.append(", status=");
		builder.append(status);
		builder.append(", statusRemark=");
		builder.append(statusRemark);
		builder.append(", measureRequired=");
		builder.append(measureRequired);
		builder.append(", measureRemark=");
		builder.append(measureRemark);
		builder.append(", comment=");
		builder.append(comment);
	}
    
}
