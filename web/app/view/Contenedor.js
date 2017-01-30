Ext.define('SGCEscalada.view.Contenedor', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.contenedor',
    layout: {
        type: 'fit'
    },

    initComponent: function () {
        var me = this;
        me.principal = Ext.widget('principal',{
            admin: me.admin,
            jurado: me.jurado,
            cliente: me.cliente,
             anonimo: me.anonimo,
            user: me.user
        });
        me.secundario = Ext.widget('secundario', {
            admin: me.admin,
            jurado: me.jurado,
            cliente: me.cliente,
            anonimo: me.anonimo
        });

        /*if(!me.admin && !me.jurado){
            me.principal.tbar = [Ext.widget('menuFase', {
                cls: 'x-btn-default-medium x-btn-blue x-btn-inner'
            })]
        }
        else if(me.jurado){

        }*/

        // items:[Ext.widget('galeriaImagenes')]
        me.items = [{
            xtype:'panel',
            layout: 'border',
            title: 'Sistema de gesti&oacute;n de competencias',
            titleAlign:'center',
            items:[me.principal/*, me.secundario*/ ]
        }];
        me.callParent(arguments);
    }
});