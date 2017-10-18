package ru.af.entity;

import java.util.List;

/**
 *
 */
public class HhResponse {

    private List<HhVacancy> items;
    private String clusters;
    private int perPage;
    private int page;
    private int pages;
    private int found;
    private String arguments;

    public List<HhVacancy> getItems() {
        return items;
    }

    public void setItems(List<HhVacancy> items) {
        this.items = items;
    }

    public String getClusters() {
        return clusters;
    }

    public void setClusters(String clusters) {
        this.clusters = clusters;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }


}
