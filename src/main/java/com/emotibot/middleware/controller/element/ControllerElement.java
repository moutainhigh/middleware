package com.emotibot.middleware.controller.element;

import org.w3c.dom.Node;

import com.emotibot.middleware.context.Context;

public interface ControllerElement
{
    public boolean init(Node node);
    
    public Context execute(Context context);
    
}
