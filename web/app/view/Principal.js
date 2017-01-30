Ext.define('SGCEscalada.view.Principal', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.principal',
    region: 'center',
    border: false,
    margins: '2 1 2 3',
    layout: 'fit',
    initComponent: function () {
        var me = this,
                menuProvincia = Ext.widget('menuProvincia', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuDeportista = Ext.widget('menuDeportista', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuCanton = Ext.widget('menuCanton', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuFederacion = Ext.widget('menuFederacion', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuLugar = Ext.widget('menuLugar', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuParroquia = Ext.widget('menuParroquia', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuCiudad = Ext.widget('menuCiudad', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuUsuario = Ext.widget('menuUsuario', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuFase = Ext.widget('menuFase', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuCompetencia = Ext.widget('menuCompetencia', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'}),
                menuMedia = Ext.widget('menuMedia', {cls: 'x-btn-default-medium x-btn-blue x-btn-inner'});
        me.tools = [{
                xtype: 'label',
                text: 'Bienvenido: '+me.user
            }, {
                type: 'button',
                text:'Salir',
                handler: function () {
                    window.location.href = 'j_spring_security_logout';
                }
            }];
        me.tbar = [];
        if (me.admin) {
            me.tbar.push(menuProvincia);
            me.tbar.push(menuFederacion);
            me.tbar.push(menuCanton);
            me.tbar.push(menuCiudad);
            me.tbar.push(menuParroquia);
            me.tbar.push(menuLugar);
            me.tbar.push(menuDeportista);
            me.tbar.push(menuCompetencia);
            me.tbar.push(menuFase);
            me.tbar.push(menuUsuario);
        }
        else if (me.jurado || me.anonimo ) {
            me.tbar.push(menuFase);
        }
        menuFase.cliente = false;
        if (!me.admin && !me.jurado && !me.anonimo ) {
            menuFase.cliente = true;
            menuDeportista.cliente = true;
            me.tbar.push(menuDeportista);
            me.tbar.push(menuFase);
        }
        if (!me.jurado) {
            me.tbar.push(menuMedia);
        }
        if(me.anonimo){
          menuMedia.anonimo=true;
        }
        me.callParent(arguments);
    }
});