<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ModelPengeluaran extends Model
{
    protected $guarded = [];
    protected $table = 'tbl_kaskeluar';
    protected $primaryKey = 'pengeluaran_id';
    // public $timestamps = false;
}
