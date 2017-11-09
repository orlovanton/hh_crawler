package ru.af.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class HhVacancy {

    private long id;
    private String name;
    private String url;
    private Date publishedAt;
    private Date createdAt;
    private boolean responseLetterRequired;
    private String description;
    private HhArea area;
    private HhSalary salary;
    private HhEmployer employer;
    private HhAddress address;
    private HhExperience experience;
    private HhSnippet snippet;
    private HhDepartment department;
    private List<HhKeySkill> keySkills;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HhSalary getSalary() {
        return salary;
    }

    public void setSalary(HhSalary salary) {
        this.salary = salary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HhArea getArea() {
        return area;
    }

    public void setArea(HhArea area) {
        this.area = area;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public HhEmployer getEmployer() {
        return employer;
    }

    public void setEmployer(HhEmployer employer) {
        this.employer = employer;
    }

    public boolean isResponseLetterRequired() {
        return responseLetterRequired;
    }

    public void setResponseLetterRequired(boolean responseLetterRequired) {
        this.responseLetterRequired = responseLetterRequired;
    }

    public HhAddress getAddress() {
        return address;
    }

    public void setAddress(HhAddress address) {
        this.address = address;
    }

    public HhExperience getExperience() {
        return experience;
    }

    public void setExperience(HhExperience experience) {
        this.experience = experience;
    }

    public HhSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(HhSnippet snippet) {
        this.snippet = snippet;
    }

    public HhDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HhDepartment department) {
        this.department = department;
    }

    public List<HhKeySkill> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<HhKeySkill> keySkills) {
        this.keySkills = keySkills;
    }
}
