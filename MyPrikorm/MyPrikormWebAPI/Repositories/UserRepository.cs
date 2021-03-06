using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.Model.DB.Context;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class UserRepository 
    {
        private ApplicationContext db;

        public UserRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<User>> GetAll()
        {
            return await db.Users.ToListAsync();
        }

        public async Task<User> Get(int id)
        {
            return await db.Users.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<User> GetByUsername(string username)
        {
            return await db.Users.FirstOrDefaultAsync(x => x.Username == username);
        }

        public async Task<User> Authenticate(string username, string password)
        {
            var user = await Task.Run(() => db.Users.SingleOrDefault(x => x.Username == username && x.Password == password));

            if (user == null)
                return null;

            return user;
        }


        public User Create(User user)
        {
            if (user != null)
            {
                db.Users.Add(user);
                Save();
            }

            return user;
        }

        public User Update(User user)
        {
            if (!db.Users.Any(x => x.Id == user.Id))
            {
                return null;
            }

            db.Update(user);
            Save();
            return user;
        }

        public User Delete(int id)
        {
            User user = db.Users.FirstOrDefault(x => x.Id == id);
            if (user == null)
            {
                return user;
            }

            db.Users.Remove(user);
            Save();
            return user;
        }

        public void Save()
        {
            db.SaveChanges();
        }
    }
}
