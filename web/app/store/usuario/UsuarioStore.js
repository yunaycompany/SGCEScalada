Ext.define('SGCEscalada.store.usuario.UsuarioStore', {
    extend: 'Ext.data.Store',
    fields: ['usuario', 'rol', 'idRol', 'idusuario',],
    proxy: {
        type: 'ajax',
        url: 'listarUsuarios.htm'
    },
    autoLoad: true
});