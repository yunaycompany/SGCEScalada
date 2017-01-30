var rolAutenticado = "",
    cliente = false,
    jurado = false,
    admin = false,
    anonimo = false,
    userAutenticado="";
function guardarUser (rol,user){
    rolAutenticado = rol;
    userAutenticado=user;
}

Ext.application({
    extend: 'Ext.app.Application',

    name: 'SGCEscalada',

    appFolder: 'app',

    views:[
        'Contenedor',
        'Principal',
        'comun.MensajeInformacion',
        'Secundario'
    ],

    controllers: [
        'PrincipalController',
        'UsuarioController',
        'DeportistaController',
        'ProvinciaController',
        'CantonController',
        'ParroquiaController',
        'CiudadController',
        'DireccionController',
        'LugarController',
        'FederacionController',
        'FaseController',
        'CompetenciaController'
    ],

    launch: function () {
      
        switch (rolAutenticado){
            case 'Administrador': {
                admin = true;
                jurado = false;
                cliente = false;
                anonimo = false;
            }
                break;
            case 'Jurado': {
                admin = false;
                jurado = true;
                anonimo = false;
                cliente = false;
            }
             case 'Anonimo': {
                admin = false;
                jurado = false;
                cliente = false;
                anonimo = true;
            }
                break;
            default :{
                admin = false;
                jurado = false;
                 anonimo = false;
                cliente = true;
            }
        }

        Ext.widget('contenedor', {
            admin: admin,
            jurado: jurado,
            cliente: cliente,
            anonimo:anonimo,
            user:userAutenticado
        });

    }
});