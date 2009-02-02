package com.mongodb.framework;

import java.util.*;
import java.net.*;

import com.mongodb.*;
import com.mongodb.util.*;


public class Capped {

    public static void main(String[] args) 
        throws UnknownHostException {

        Mongo m = new Mongo( new DBAddress( "127.0.0.1:27017/driver_test_framework" ) );
        DBObject foo = new BasicDBObject();
        foo.put( "create", "capped1" );
        foo.put( "capped", true );
        foo.put( "size", 500 );
        DBObject dbobj = m.command( foo );
        DBCollection c = m.getCollection( "capped1" );

        DBObject obj1 = new BasicDBObject();
        obj1.put( "x", 1 );
        c.save( obj1 );
        DBObject obj2 = new BasicDBObject();
        obj2.put( "x", 2 );
        c.save( obj2 );

        foo.put( "create", "capped2" );
        foo.put( "size", 1000 );
        m.command( foo );
        String s = "";
        c = m.getCollection( "capped2" );
        for( int i=1; i<= 100; i++ ) {
            DBObject obj = new BasicDBObject();
            obj.put( "dashes", s );
            c.save( obj );
            s = s+"-";
        }
    }
}
