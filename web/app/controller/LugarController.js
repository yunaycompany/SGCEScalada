Ext.define('SGCEscalada.controller.LugarController', {
    extend: 'Ext.app.Controller',
    views: [
        'lugar.AdicionarLugar',
        'lugar.EliminarLugar',
        'direccion.FsDireccion',
        'provincia.CbxProvincias',
        'canton.CbxCantones',
        'parroquia.CbxParroquias',
        'lugar.MenuLugar'
    ],
    stores: ['provincia.ProvinciasStore', 'canton.CantonesStore', 'parroquia.ParroquiasStore', 'lugar.LugaresStore'],
    refs: [
        {
            ref: 'adicionarLugar',
            selector: 'adicionarLugar'
        },
        {
            ref: 'menuLugar',
            selector: 'menuLugar'
        },
        {
            ref: 'eliminarLugar',
            selector: 'eliminarLugar'
        },
        {
            ref: 'cbxLugares',
            selector: 'cbxLugares'
        }
    ],
    init: function() {
        this.control({
            'adicionarLugar button[action=aceptarAdicionarLugar]': {
                click: this.onClickAceptarAdicionarLugar
            },
            'eliminarLugar button[action=eliminarLugar]': {
                click: this.onClickEliminarLugar
            },
            'adicionarLugar button[action=cancelarAdicionarLugar]': {
                click: this.onClickCancelarAdicionarLugar
            },
            'menuLugar component[action=menuAdicionarLugar]': {
                click: this.onClickMenuAdicionarLugar
            },
            'menuLugar component[action=menuEliminarLugar]': {
                click: this.onClickMenuEliminarLugar
            }
        });
    },
    onClickEliminarLugar: function(th) {
        var me = this,
                form = th.up('form').getForm();
        form.submit({
            url: 'eliminarLugar.htm',
            method: 'POST',
            success: function(form) {
                Ext.widget('msjInformacion', {mensaje: 'Lugar eliminado satisfactoriamente.'});
                form.reset();
                me.getCbxLugares().getStore().load();

            },
            failure: function(form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickAceptarAdicionarLugar: function() {
        var me = this,
                form = me.getAdicionarLugar().frmAdicionarLugar.getForm();
        form.submit({
            url: 'insertarLugar.htm',
            method: 'POST',
            success: function(form) {
                Ext.widget('msjInformacion', {mensaje: 'Lugar insertado satisfactoriamente.'});
                form.reset();

            },
            failure: function(form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickCancelarAdicionarLugar: function(th) {
        th.up('window').destroy();
    },
    onClickMenuAdicionarLugar: function() {
        Ext.widget('adicionarLugar');
    },
    onClickMenuEliminarLugar: function() {
        Ext.widget('eliminarLugar');
    }
});