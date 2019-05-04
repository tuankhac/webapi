# webapi
    This is document describe about NEO API.
    This API is updated version was based on old API. Mechanism is creation a file sql.properties which declare a couple key value. 
    Key is name which we declare for api code, value is mapped with statement in database like MySQL, Oracle, SQL Server .... 
    
    This current version apply for Oracle.

    API: We use methods as: 

    + qry: using for statement like "select" of database and return is resultset represent list of json API. 
    This method support for all request method is GET.

    + ref : using for procedures and functions and return is resultset represent list of json API. 
    This method support for all request method is GET.

    + val1 : using for procedures and functions and return is datatype of database represent with object data of API 
    like "String, integer, float, double". This method support for all request method is POST
    ( can use PUT,PATCH,DELETE but it is not necessary). Method "GET" shouldn't use because it is not secure. This method support 
    for paramter put on header.
    
    + val2 : using for procedures and functions and return is datatype of database represent with object data of API 
    like "String, integer, float, double". This method support for all request method is POST
    ( can use PUT,PATCH,DELETE but it is not necessary). Method "GET" shouldn't use because it is not secure. This method support 
    for paramter put on body.
    
    + update : using for statement like "insert, update, delete" of database and 
    return is int of database and represent int of API. This method support for all request method is POST
    ( can use PUT,PATCH,DELETE but it is not necessary). Method "GET" shouldn't use because it is not secure.
    
    + To use API. First, we need to know function or procedure was written on database, and then we will config Map<key,value> 
    in sql.propties file. Ex1. insert_book= insert into books(?,?,?).
    Every function or procedure in sql.properties file, which has number of parameter larger than 1. 
    It mean we need to config 2 rows for this case. With second row is sort of parameter represent with paramters
    put into function or procedure in database. With above Ex1, we need 3 parameters put into statement. Therefore, we need to config 2 rows
    for this case. First row : insert_book= insert into books(?,?,?) and second row : insert_book.param=pid,pname,pauthor .
    As you can see, we use insert_book.param is key and pid,pname,pauthor is list of parameters and they is splitted by comma. Name of them 
    are name of paramters were put from client. 
    With statement dont need to put any parameter, yep we dont need to config 2 rows, simple is 1 row like this.
    Ex2: update_book=update book set name='Life of Steve Job' where id =1
    
    + Security: Oh. To can pass over all of api. We need to authorize first. In mean we need to login by send 
    user/pass(encrypt by algorithm we regulated). When we logged in successfully, we will get object contains info about account we 
    logged in and it contains token. Every time we call api we need to put token with key is "Authorization" into 
    header of every request.
    That is very simple. Right!.
    Ok . Let us enjoy.
