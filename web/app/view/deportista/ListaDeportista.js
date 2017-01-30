Ext.define('SGCEscalada.view.deportista.ListaDeportista', {
    extend: 'Ext.window.Window',
    alias: 'widget.listaDeportista',
    title: 'Lista de Deportistas',
    autoShow: true,
    width: 680,
    height: '70%',
    resizable: false,
    layout: 'fit',
    model: 'true',

    initComponent: function () {
        this.items = [
            {
                xtype: 'gridpanel',
                store:'deportista.DeportistaStore',
                border: 0,
                columns: [
                    {header: 'Usuario', dataIndex: 'nombre'},
                    {header: '1er. Apellido', dataIndex: 'apellido1', width: 120},
                    {header: '2do. Apellido', dataIndex: 'apellido2', width: 120},
                    {header: 'Sexo', dataIndex: 'sexo', width: 70},
                    {header: 'Fecha Nacimiento', dataIndex: 'nacimiento', width:150},
                    {header: 'Provincia', dataIndex: 'provincia'},
                    {header: 'Cant&oacute;n', dataIndex: 'canton'},
                    {header: 'Parroquia', dataIndex: 'parroquia'}
                ]
            }
        ];
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
        ];

        this.callParent(arguments);
    }
});