Ext.define('SGCEscalada.view.usuario.ListaUsuario', {
    extend: 'Ext.window.Window',
    alias: 'widget.ListaUsuario',
    title: 'Lista de Usuarios',
    autoShow: true,
    width: 300,
    height: '60%',
    resizable: false,
    layout: 'fit',
    modal: true,

    initComponent: function () {
        this.items = [
            {
                xtype: 'gridpanel',
                store:'usuario.UsuarioStore',
                border:false,
                frame:false,
                columns: [
                    {header: 'Usuario', dataIndex: 'usuario',flex:1},
                    {header: 'Rol', dataIndex: 'rol', rol: 1, flex: 1}
                ]
            }
        ],
            this.buttons = [
                {
                    text: 'Eliminar',
                    action: 'Eliminar'
                },
                {
                    text: 'Modificar',
                    action: 'Modificar'
                },
                {
                    text: 'Adicionar',
                    action: 'Adicionar'
                }
            ]

        this.callParent(arguments);
    }
});