@extends('main.index')

@section('menu')
<div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <div class="page-title-right">
                <ol class="breadcrumb m-0">
                    <li class="breadcrumb-item"><a href="javascript: void(0);">{{ $menu1 }}</a></li>
                    <li class="breadcrumb-item"><a href="javascript: void(0);">{{ $menu2 }}</a></li>
                    <li class="breadcrumb-item active">{{ $menu3 }}</li>
                </ol>
            </div>
            <h4 class="page-title">Dana Masuk</h4>
        </div>
    </div>
</div>

@if (session('user'))

      <div class="alert alert-success" role="alert">
        <i class="mdi mdi-check-all mr-2"></i>  {{ session('user') }}
    </div>
@endif

<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <div class="row mb-2">
                    <div class="col-sm-4">
                        <a data-toggle="modal" data-target="#add-dokter" href="javascript:void(0);" class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle mr-2"></i> Add Docter</a>
                    </div>
                    <div class="col-sm-8">
                        <div class="text-sm-right">
                            <button type="button" class="btn btn-success mb-2 mr-1"><i class="mdi mdi-cog"></i></button>
                            <button type="button" class="btn btn-light mb-2 mr-1">Import</button>
                            <button type="button" class="btn btn-light mb-2">Export</button>
                        </div>
                    </div><!-- end col-->
                </div>

                <div class="table-responsive">
                    <table class="table table-centered table-striped dt-responsive nowrap w-100" id="products-datatable">
                        <thead>
                            <tr>
                                <th style="width: 20px;">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck1">
                                        <label class="custom-control-label" for="customCheck1">&nbsp;</label>
                                    </div>
                                </th>
                                <th>Customer</th>
                                <th>Phone</th>
                                <th>Level </th>
                                <th>Address</th>
                                <th>Created at</th>
                                <th>Status</th>
                                <th style="width: 75px;">Action</th>
                            </tr>
                        </thead>

                    </table>
                </div>
            </div> <!-- end card-body-->
        </div> <!-- end card-->
    </div> <!-- end col -->
</div>


{{-- add form model --}}

<form method="POST" action="/create_dokter" enctype="multipart/form-data">
    @csrf
    <div id="add-dokter" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">


        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title">New Doctor</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body p-4">


                   <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="field-3" class="control-label">Nama Lengkap</label>
                            <input type="text" name="nama"  class="form-control" id="field-3" placeholder="Nama Lengkap">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="field-3" class="control-label">Username</label>
                            <input type="text" name="username" required="required" class="form-control" id="field-3" placeholder="Username">
                        </div>
                    </div>
                </div>
               

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="field-3" class="control-label">Password</label>
                            <input type="password"  name="password" required="required" class="form-control" id="field-3" placeholder="Password">
                        </div>
                    </div>
                </div>
                

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="field-1" class="control-label">Phone</label>
                            <input type="text" maxlength="12" name="phone" class="form-control" id="field-1" required="required" placeholder="+62">
                        </div>
                    </div>
                   
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="field-2"  class="control-label">Level</label>
                            <select name="level" required="required" class="form-control">
                                <option value="">Pilih</option>
                                <option value="admin">Admin</option>
                                <option value="dokter">Dokter</option>
                                <option value="ceo">CEO</option>
                               
                            </select>
                        </div>
                    </div>
                </div>

                
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group no-margin">
                            <label for="field-7" class="control-label">Alamat Rumah</label>
                            <textarea name="remember_token" required="required" class="form-control" id="field-7" placeholder="Enter Text"></textarea>
                        </div>
                        
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">

                         <h4 class="header-title">Image Profil</h4>
                        <p class="sub-header">
                           Set your Profil Image.
                        </p>

                        <input type="file" required  name="image" class="form-control" width="100" />
                        
                    </div>

                
                  
                       



               

            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-info waves-effect waves-light">Ok, Simpan</button>
        </div>



    </div>
</div>

</div>

</form>


{{-- and add --}}

@endsection
