/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aems.graphql;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.aems.database.AEMSDatabase;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;

/**
 *
 * @author Sebastian
 */
public class NotificationException extends GraphQLObjectType {
    
    private static NotificationException instance;
    
    private static final GraphQLFieldDefinition ID = Query.getFieldDefinition("id", AEMSDatabase.NOTIFICATION_EXCEPTIONS, AEMSDatabase.NotificationExceptions.ID, Scalars.GraphQLInt);
    private static final GraphQLFieldDefinition NOTIFICATION = Query.getFieldDefinition("notification", Notification.getInstance());
    private static final GraphQLFieldDefinition PERIOD = Query.getFieldDefinition("period", Period.getInstance());
    private static final GraphQLFieldDefinition MIN_POSITIVE_DEVIATION = Query.getFieldDefinition("min_positive_deviation", AEMSDatabase.NOTIFICATION_EXCEPTIONS, AEMSDatabase.NotificationExceptions.MIN_POSITIVE_DEVIATION, Scalars.GraphQLFloat);
     private static final GraphQLFieldDefinition MIN_NEGATIVE_DEVIATION = Query.getFieldDefinition("min_negative_deviation", AEMSDatabase.NOTIFICATION_EXCEPTIONS, AEMSDatabase.NotificationExceptions.MIN_NEGATIVE_DEVIATION, Scalars.GraphQLFloat);
    
    
    public NotificationException(String name, String description, List<GraphQLFieldDefinition> fieldDefinitions, List<GraphQLOutputType> interfaces) {
        super(name, description, fieldDefinitions, interfaces);
    }
    
    public static NotificationException getInstance() {
        if(instance != null)
            return instance;
        
        ArrayList<GraphQLFieldDefinition> defs = new ArrayList<>();
        defs.add(ID);
        defs.add(NOTIFICATION);
        defs.add(PERIOD);
        defs.add(MIN_POSITIVE_DEVIATION);
        defs.add(MIN_NEGATIVE_DEVIATION);
        
        instance = new NotificationException("notification_exception", "", defs, new ArrayList<GraphQLOutputType>());
        return instance;
    }
    
}
