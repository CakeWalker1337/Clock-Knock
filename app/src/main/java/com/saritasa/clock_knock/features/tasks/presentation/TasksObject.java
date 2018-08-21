package com.saritasa.clock_knock.features.tasks.presentation;

import java.util.Objects;

public class TasksObject{

    private String key;
    private String id;
    private String priorityIconUrl;
    private String projectAvatarUrl;
    private String status;
    private String summary;

    @Override
    public int hashCode(){

        return Objects.hash(key, id, priorityIconUrl, projectAvatarUrl, status, summary);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksObject that = (TasksObject) aO;
        return Objects.equals(key, that.key) &&
                Objects.equals(id, that.id) &&
                Objects.equals(priorityIconUrl, that.priorityIconUrl) &&
                Objects.equals(projectAvatarUrl, that.projectAvatarUrl) &&
                Objects.equals(status, that.status) &&
                Objects.equals(summary, that.summary);
    }

    @Override
    public String toString(){
        return "TasksDomain{" +
                "key='" + key + '\'' +
                ", id='" + id + '\'' +
                ", priorityIconUrl='" + priorityIconUrl + '\'' +
                ", projectAvatarUrl='" + projectAvatarUrl + '\'' +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }

    public String getKey(){
        return key;
    }

    public void setKey(final String aKey){
        key = aKey;
    }

    public String getId(){
        return id;
    }

    public void setId(final String aId){
        id = aId;
    }

    public String getPriorityIconUrl(){
        return priorityIconUrl;
    }

    public void setPriorityIconUrl(final String aPriorityIconUrl){
        priorityIconUrl = aPriorityIconUrl;
    }

    public String getProjectAvatarUrl(){
        return projectAvatarUrl;
    }

    public void setProjectAvatarUrl(final String aProjectAvatarUrl){
        projectAvatarUrl = aProjectAvatarUrl;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(final String aStatus){
        status = aStatus;
    }

    public String getSummary(){
        return summary;
    }

    public void setSummary(final String aSummary){
        summary = aSummary;
    }

}
