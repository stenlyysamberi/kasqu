<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ModelMitra extends Model
{
    protected $guarded = [];
    protected $table = 'tbl_mitra_kampus';
    protected $primaryKey = 'mitra_id';
    public $timestamps = false;

    /**
     * Get the user that owns the ModelMitra
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function mitras(){
        return $this->belongsTo(UserMan::class);
    }
}
