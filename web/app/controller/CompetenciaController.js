Ext.define('SGCEscalada.controller.CompetenciaController', {
    extend: 'Ext.app.Controller',

    views: [
        'competencia.AdicionarCompetencia',
        'competencia.EliminarCompetencia',
        'competencia.MenuCompetencia',
        'Principal',
        'competencia.AdicionarDeportistaCompetencia',
        'competencia.CbxCompetencias',
        'deportista.GrdDeportistaCompetencia',
        'deportista.AdicionarDeportista'
    ],
    stores: [
        'competencia.CompetenciasStore',
        'competencia.DeportistasCompetenciaStore'
    ],
    refs: [
        {
            ref: 'adicionarCompetencia',
            selector: 'adicionarCompetencia'
        },
        {
            ref: 'eliminarCompetencia',
            selector: 'eliminarCompetencia'
        },
        {
            ref: 'menuCompetencia',
            selector: 'menuCompetencia'
        },
        {
            ref: 'principal',
            selector: 'principal'
        },
        {
            ref: 'adicionarDeportistaCompetencia',
            selector: 'adicionarDeportistaCompetencia'
        },
        {
            ref: 'grdDeportistaCompetencia',
            selector: 'grdDeportistaCompetencia'
        },
        {
            ref: 'cbxCompetencias',
            selector: 'cbxCompetencias'
        },
        {
            ref: 'cbxLugares',
            selector: 'cbxLugares'
        },
        {
            ref: 'adicionarDeportista',
            selector: 'adicionarDeportista'
        }
    ],

    init: function () {
        this.control({
            'menuCompetencia component[action=menuAdicionarCompetencia]': {
                click: this.onClickMenuAdicionarCompetencia
            },
            'menuCompetencia component[action=menuEliminarCompetencia]': {
                click: this.onClickMenuEliminarCompetencia
            },
            'menuCompetencia component[action=menuAdicionarDeportistaCompetencia]': {
                click: this.onClickMenuAdicionarDeportistaCompetencia
            },
            'adicionarCompetencia component[action=aceptarAdicionarCompetencia]': {
                click: this.onClickAceptarAdicionarCompetencia
            },
            'eliminarCompetencia component[action=aceptarEliminarCompetencia]': {
                click: this.onClickAceptarEliminarCompetencia
            },
            'eliminarCompetencia component[action=cancelarAdicionarCompetencia]': {
                click: this.onClickCancelarAdicionarCompetencia
            },
            'adicionarCompetencia component[action=cancelarAdicionarCompetencia]': {
                click: this.onClickCancelarAdicionarCompetencia
            },
            'adicionarDeportistaCompetencia component[action=adicionarDeportistaGrdDeportistaCompetencia]': {
                click: this.onClickAdicionarDeportistaGrdDeportistaCompetencia
            },
            'adicionarDeportistaCompetencia component[action=cancelarAdicionarDeportistaCompetencia]': {
                click: this.onClickCancelarAdicionarDeportistaCompetencia
            },
            'adicionarDeportistaCompetencia component[action=aceptarAdicionarDeportistaCompetencia]': {
                click: this.onClickAceptarAdicionarDeportistaCompetencia
            },
            'adicionarDeportista': {
                destroy: this.onDestroyAdicionarDeportistaAdicionarDeportistaCompetencia
            },
            'cbxCompetencias': {
                select: this.onSelectCompetenciaAdicionarDeportistaCompetencia
            },
            'cbxLugares': {
                select: this.onSelectLugarEliminarCompetencia
            },
            'grdDeportistaCompetencia': {
                render: this.onRenderGrdDeportistaCompetencia
            }
        });
    },

    onClickMenuAdicionarCompetencia: function(){
        Ext.widget('adicionarCompetencia');
    },

    onClickMenuEliminarCompetencia: function(){
        Ext.widget('eliminarCompetencia');
    },

    onSelectCompetenciaAdicionarDeportistaCompetencia: function(){
        var me = this;
        if(me.getGrdDeportistaCompetencia()){
            me.getGrdDeportistaCompetencia().getStore().removeAll();
            me.getGrdDeportistaCompetencia().getStore().load({
                params: {
                    idCompetencia: me.getCbxCompetencias().value
                }
            });
        }
    },

    onSelectLugarEliminarCompetencia: function(){
        var me = this;
            me.getCbxCompetencias().getStore().load({
                params: {
                    idLugar: me.getCbxLugares().value
                }
            });
        me.getCbxCompetencias().setDisabled(false);
    },

    onClickMenuAdicionarDeportistaCompetencia: function(){
        Ext.widget('adicionarDeportistaCompetencia');
    },

    onClickAceptarAdicionarCompetencia: function(th){
        var form = th.up('form').getForm();

        form.submit({
            url: 'insertarCompetencia.htm',
            method: 'GET',
            success: function (form) {
                Ext.widget('msjInformacion',{mensaje: 'Competencia insertada satisfactoriamente.'});
                form.reset();
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error', action.result.msg);
                form.reset();
            }
        });
    },

    onClickAceptarEliminarCompetencia: function(th){
        var me = this,
            form = th.up('form').getForm();

        form.submit({
            url: 'eliminarCompetencia.htm',
            method: 'GET',
            success: function (form) {
                Ext.widget('msjInformacion',{mensaje: 'Competencia insertada satisfactoriamente.'});
                form.reset();
                me.getPrincipal().dtvCompetencias.getStore().load();
                me.getCbxCompetencias().getStore().load();

            },
            failure: function (form, action) {
                Ext.Msg.alert('Error', action.result.msg);
                form.reset();
            }
        });
    },

    onClickCancelarAdicionarCompetencia: function(th){
        th.up('window').destroy();
    },

    onClickCancelarAdicionarDeportistaCompetencia: function(th){
        th.up('window').destroy();
    },

    onClickAdicionarDeportistaGrdDeportistaCompetencia: function(){
        Ext.widget('adicionarDeportista');
    },

    onDestroyAdicionarDeportistaAdicionarDeportistaCompetencia: function(){
        var me = this;
        me.getGrdDeportistaCompetencia().getStore().removeAll();
        if(me.getCbxCompetencias().value){
            me.getGrdDeportistaCompetencia().getStore().load({
                params: {
                    idCompetencia: me.getCbxCompetencias().value
                }
            });
        }
        else{
            me.getGrdDeportistaCompetencia().getStore().load();
        }
    },

    onRenderGrdDeportistaCompetencia: function(th){
        th.getStore().load();
    },

    onClickAceptarAdicionarDeportistaCompetencia: function(th){
        var me = this,
            grdDeportistaCompStr = me.getGrdDeportistaCompetencia().getStore(),
            arr = [],
            frm = th.up('form').getForm();

        grdDeportistaCompStr.each(function(r){
            if(r.data.adicionado){
                arr.push({
                    idCompetencia: me.getCbxCompetencias().value,
                    idDeportista: r.data.iddeportista,
                    idSexo: r.data.idSexo
                });
            }
        });

        frm.submit({
            url: 'inscribirDeportistasEnFases.htm',
            method:'GET',
            params:{
                deportistas:Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Deportista(s) inscrito(s) satisfactoriamente.'});
                me.onDestroyAdicionarDeportistaAdicionarDeportistaCompetencia();
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
                me.onDestroyAdicionarDeportistaAdicionarDeportistaCompetencia();
            }
        });
    }
});