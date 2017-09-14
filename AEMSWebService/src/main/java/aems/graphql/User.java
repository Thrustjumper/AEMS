package aems.graphql;

import aems.Condition;
import aems.database.AEMSDatabase;
import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Sebastian
 */
public class User extends GraphQLObjectType {
    
    private static User instance;
    
    private static final GraphQLFieldDefinition ID = GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLInt).dataFetcher(new DataFetcher<Integer> () {
        @Override
        public Integer get(DataFetchingEnvironment environment) {
            final JSONObject OBJ = new JSONObject(environment.getSource().toString());
            return Integer.valueOf(Query.execQuery(OBJ, AEMSDatabase.USERS, AEMSDatabase.Users.ID.name(), new Condition(".*AT.*") {
                @Override
                public String exec() {
                    return Query.execQuery(OBJ, AEMSDatabase.METERS, AEMSDatabase.Meters.USER.name(), AEMSDatabase.USERS, AEMSDatabase.Users.ID.name(), AEMSDatabase.Users.ID.name());
                }
            }));
        }
    }).build();
    
    private static final GraphQLFieldDefinition USERNAME = GraphQLFieldDefinition.newFieldDefinition().name("username").type(Scalars.GraphQLString).dataFetcher(new DataFetcher<String> () {
        @Override
        public String get(DataFetchingEnvironment environment) {
            final JSONObject OBJ = new JSONObject(environment.getSource().toString());
            return Query.execQuery(OBJ, AEMSDatabase.USERS, AEMSDatabase.Users.USERNAME.name(), new Condition(".*AT.*") {
                @Override
                public String exec() {
                    return Query.execQuery(OBJ, AEMSDatabase.METERS, AEMSDatabase.Meters.USER.name(), AEMSDatabase.USERS, AEMSDatabase.Users.ID.name(), AEMSDatabase.Users.USERNAME.name());
                }
            });
        }
    }).build();
    
    public User(String name, String description, List<GraphQLFieldDefinition> fieldDefinitions, List<GraphQLOutputType> interfaces) {
        super(name, description, fieldDefinitions, interfaces);
    }
    
    public static User getInstance() {
        if(instance != null)
            return instance;
        
        ArrayList<GraphQLFieldDefinition> defs = new ArrayList<>();
        defs.add(ID);
        defs.add(USERNAME);
        
        instance = new User("user", "", defs, new ArrayList<GraphQLOutputType>());
        return instance;
    }
    
}
