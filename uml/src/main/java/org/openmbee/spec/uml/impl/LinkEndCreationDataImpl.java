package org.openmbee.spec.uml.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SecondaryTable;
import javax.persistence.Transient;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Table;
import org.openmbee.spec.uml.Comment;
import org.openmbee.spec.uml.Element;
import org.openmbee.spec.uml.InputPin;
import org.openmbee.spec.uml.LinkEndCreationData;
import org.openmbee.spec.uml.Property;
import org.openmbee.spec.uml.QualifierValue;
import org.openmbee.spec.uml.jackson.MofObjectDeserializer;
import org.openmbee.spec.uml.jackson.MofObjectSerializer;

@Entity
@SecondaryTable(name = "LinkEndCreationData")
@Table(appliesTo = "LinkEndCreationData", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "LinkEndCreationData")
@JsonTypeName(value = "LinkEndCreationData")
public class LinkEndCreationDataImpl extends MofObjectImpl implements LinkEndCreationData {

    private Element owner;
    private Collection<Element> ownedElement;
    private InputPin value;
    private Collection<QualifierValue> qualifier;
    private InputPin insertAt;
    private Property end;
    private Boolean isReplaceAll;
    private Collection<Comment> ownedComment;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Element getOwner() {
        return owner;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(Collection<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "InputPinMetaDef", metaColumn = @Column(name = "valueType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "valueId", table = "LinkEndCreationData")
    public InputPin getValue() {
        return value;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = InputPinImpl.class)
    public void setValue(InputPin value) {
        this.value = value;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "QualifierValueMetaDef", metaColumn = @Column(name = "qualifierType"), fetch = FetchType.LAZY)
    @JoinTable(name = "LinkEndCreationData_qualifier",
        joinColumns = @JoinColumn(name = "LinkEndCreationDataId"),
        inverseJoinColumns = @JoinColumn(name = "qualifierId"))
    public Collection<QualifierValue> getQualifier() {
        if (qualifier == null) {
            qualifier = new ArrayList<>();
        }
        return qualifier;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = QualifierValueImpl.class)
    public void setQualifier(Collection<QualifierValue> qualifier) {
        this.qualifier = qualifier;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "InputPinMetaDef", metaColumn = @Column(name = "insertAtType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "insertAtId", table = "LinkEndCreationData")
    public InputPin getInsertAt() {
        return insertAt;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = InputPinImpl.class)
    public void setInsertAt(InputPin insertAt) {
        this.insertAt = insertAt;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "PropertyMetaDef", metaColumn = @Column(name = "endType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "endId", table = "LinkEndCreationData")
    public Property getEnd() {
        return end;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PropertyImpl.class)
    public void setEnd(Property end) {
        this.end = end;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Column(name = "isReplaceAll", table = "LinkEndCreationData")
    public Boolean isReplaceAll() {
        return isReplaceAll;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setReplaceAll(Boolean isReplaceAll) {
        this.isReplaceAll = isReplaceAll;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @Column(name = "ownedCommentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "LinkEndCreationData_ownedComment",
        joinColumns = @JoinColumn(name = "LinkEndCreationDataId"),
        inverseJoinColumns = @JoinColumn(name = "ownedCommentId"))
    public Collection<Comment> getOwnedComment() {
        if (ownedComment == null) {
            ownedComment = new ArrayList<>();
        }
        return ownedComment;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CommentImpl.class)
    public void setOwnedComment(Collection<Comment> ownedComment) {
        this.ownedComment = ownedComment;
    }

}
