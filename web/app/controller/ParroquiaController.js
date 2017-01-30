Ext.define('SGCEscalada.controller.ParroquiaController', {
    extend: 'Ext.app.Controller',
    views: [
        'parroquia.AdicionarParroquia',
        'parroquia.EliminarParroquia',
        'parroquia.MenuParroquia',
        'ciudad.CbxCiudad'
    ],
    refs: [
        {
            ref: 'adicionarParroquia',
            selector: 'adicionarParroquia'
        },
        {
            ref: 'menuParroquia',
            selector: 'menuParroquia'
        },
        {
            ref: 'eliminarParroquia',
            selector: 'eliminarParroquia'
        },
        {
            ref: 'cbxParroquias',
            selector: 'cbxParroquias'
        }
    ],
    stores: ['ciudad.CiudadStore', 'canton.CantonesStore', 'provincia.ProvinciasStore'],
    init: function() {
        this.control({
            'adicionarParroquia combo[action=adicionarParroquiaProvincia]': {
                select: this.onSelectProvinciaAdicionarParroquia
            },
            'adicionarParroquia combo[action=adicionarParroquiaCanton]': {
                select: this.onSelectCantonAdicionarParroquia
            },
             'adicionarParroquia combo[action=adicionarParroquiaCiudad]': {
                beforeload: this.onBeforeLoadCiudadAdicionarParroquia
            },
            'adicionarParroquia button[action=aceptarAdicionarParroquia]': {
                click: this.onClickAceptarAdicionarParroquia
            },
            'eliminarParroquia button[action=eliminarParroquia]': {
                click: this.onClickEliminarParroquia
            },
            'adicionarParroquia button[action=cancelarAdicionarParroquia]': {
                click: this.onClickCancelarAdicionarParroquia
            },
            'eliminarParroquia button[action=cancelarEliminarParroquia]': {
                click: this.onClickCancelarAdicionarParroquia
            },
            'menuParroquia component[action=menuAdicionarParroquia]': {
                click: this.onClickMenuAdicionarParroquia
            },
            'menuParroquia component[action=menuEliminarParroquia]': {
                click: this.onClickMenuEliminarParroquia
            }
        });
    },
    onSelectProvinciaAdicionarParroquia: function(th, records) {
        var me = this,
                cbxCanton = me.getAdicionarParroquia().cbxCantonAdicionarParroquia;
        cbxCanton.getStore().on('beforeload', function(action, option) {
            option.params = {};
            option.params.idProvincia = records[0].data.idProvincia;
        });
        cbxCanton.reset();
        cbxCanton.getStore().load();
        cbxCanton.setDisabled(false);
    },
    onSelectCantonAdicionarParroquia: function(th,records) {
        var me = this,
                cbxCiudad = me.getAdicionarParroquia().cbxCiudadAdicionarParroquia;
        cbxCiudad.getStore().on('beforeload', function(action, option) {
            option.params = {};
            option.params.idCanton = records[0].data.idCanton;
        });
        cbxCiudad.reset();
        cbxCiudad.getStore().load();
        cbxCiudad.setDisabled(false);
    },
    onBeforeLoadCiudadAdicionarParroquia: function(th) {      
    },
    onClickAceptarAdicionarParroquia: function() {
        var me = this,
                form = me.getAdicionarParroquia().frmAdicionarParroquia.getForm();
        form.submit({
            url: 'insertarParroquia.htm',
            method: 'POST',
            success: function(form) {
                Ext.widget('msjInformacion', {mensaje: 'Parroquia insertada satisfactoriamente.'});
                form.reset();
                
            },
            failure: function(form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickEliminarParroquia: function(th) {
        var me = this,
                form = th.up('form').getForm();
        form.submit({
            url: 'eliminarParroquia.htm',
            method: 'POST',
            success: function(form) {
                Ext.widget('msjInformacion', {mensaje: 'Parroquia eliminada satisfactoriamente.'});
                form.reset();
                me.getCbxParroquias().getStore().load();
               
            },
            failure: function(form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickCancelarAdicionarParroquia: function(th) {
        th.up('window').destroy();
    },
    onClickMenuAdicionarParroquia: function() {
        Ext.widget('adicionarParroquia');
    },
    onClickMenuEliminarParroquia: function() {
        Ext.widget('eliminarParroquia');
    }
});