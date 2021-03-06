package com.ipe.module.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tangdu
 * Date: 13-10-5
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "t_cor_menu", schema = "", catalog = "db_work")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"parent"})
public class Menu extends IDEntity {

    private String menuName;

    @Column(name = "menu_name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @JSONField(name = "text")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    private String menuType;

    @Column(name = "menu_type", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    private String menuUrl;

    @Column(name = "menu_url", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    private String menuStyle;

    @Column(name = "menu_style", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    private Integer sno;

    @Column(name = "sno_", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    private String enabled;

    @Column(name = "enabled_", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
    @Basic
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    private String remark;

    @Column(name = "remark_", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Timestamp createdDate;

    @Column(name = "created_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    private Timestamp updatedDate;

    @Column(name = "updated_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id_")
    @NotFound(action = NotFoundAction.IGNORE)
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    ///tree 配置
    private Set<Menu> rows;
    private Menu parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy(value = "sno")
    @JSONField(name = "menu")
    public Set<Menu> getRows() {
        return rows;
    }

    public void setRows(Set<Menu> rows) {
        this.rows = rows;
        if (rows == null || rows.isEmpty()) {
            this.leaf = true;
        }
    }

    private boolean leaf = false;

    @Transient
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    private boolean expanded = true;

    @Transient
    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    private Boolean checked = null;

    @Transient
    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
