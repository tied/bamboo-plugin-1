package com.mabl;

import com.atlassian.bamboo.build.PlanResultsAction;
import com.opensymphony.xwork2.Action;

public class ViewMablOutput extends PlanResultsAction {

    public String execute() {
        return Action.SUCCESS;
    }
}
