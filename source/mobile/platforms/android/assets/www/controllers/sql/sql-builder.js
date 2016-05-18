'use strict';

function GeradorSql(){

}

function gerar_insert(obj, pk, tabela){
    var sql = "insert into "+tabela;
    var campos = "";
    var values = "";
    var valores = [];
    for(var attr in obj){
        if (attr == pk) { continue ;};
        campos += (' '+attr +',');
        values += (' ?,');
        valores.push(obj[attr]);
    }
    var sql = sql + "( "+campos+ ") values ("+values + ");";
    sql = sql.replace(/\,\)/g, ')');
    return {'sql':sql, 'values': valores};
}


function gerar_update(obj, pk, tabela, campos_excessao){
    var sql = "update "+tabela+" set ";
    var campos = "";
    var valores = [];
    if (campos_excessao == undefined) { campos_excessao = [];};
    for(var attr in obj){
        if (attr == pk) { continue ;};
        if (campos_excessao.indexOf(attr) >= 0) {continue;};
        campos += (' '+attr +' = ?,');
        valores.push(obj[attr]);
    }
    valores.push(obj[pk]);
    campos = campos.substr(0,campos.length - 1);
    var sql = sql + campos+ " where "+pk + " = ?;";
    return {'sql':sql, 'values': valores};
}