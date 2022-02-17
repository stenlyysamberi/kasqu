<?php

namespace App\Http\Controllers;

use App\ModelMitra;
use Illuminate\Http\Request;

class ControllerMitra extends Controller
{
    public function index(){
        $mitra = ModelMitra::all();
        return($mitra);
    }
}
